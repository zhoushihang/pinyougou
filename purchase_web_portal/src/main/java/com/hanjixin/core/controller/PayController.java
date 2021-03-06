package com.hanjixin.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hanjixin.core.service.PayService;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 支付管理
 *
 * 微信扫码
 *
 */
@RestController
@RequestMapping("/pay")
public class PayController {
    @Reference
    private PayService payService;
    //生成二维码
    @RequestMapping("/createNative")
    public Map<String,String> createNative(){
        //当前登陆人
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return payService.createNative(name);
    }

    @RequestMapping("/queryPayStatus")
    public Result queryPayStatus(String out_trade_no){
        try {
            int x = 0;
            while (true){
                Map<String,String> map = payService.queryPayStatus(out_trade_no);
                //未支付 再查
                if("NOTPAY".equals(map.get(out_trade_no))){
                    //睡一会
                    Thread.sleep(3000);
                    x++;
                    if(x>200){
                        //再次调用 微信服务器Api  关闭订单(同学写了)
                        return new Result(false,"支付超时");
                    }
                }
                else{
                    return new Result(true,"支付成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"支付失败");
        }
    }
 }
