package com.zerobase.convpay;

import com.zerobase.convpay.config.ApplicationConfig;
import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;

public class UserClient {
    public static void main(String[] args) {
        // '사용자' -> 편결이 -> 머니어댑터

        // 📌 변경 전: ConveniencePayService 객체를 직접 생성하였음.
        // ConveniencePayService conveniencePayService = new ConveniencePayService();

        // 📌 변경 후: 자체 프레임워크인 ApplicationConfig를 통해 미리 설정된 ConveniencePayService 객체를 받아옴.

        ApplicationConfig applicationConfig = new ApplicationConfig();
        ConveniencePayService conveniencePayService = applicationConfig.conveniencePayServiceDiscountedByPayMethod();

        // 결제 1000원
        PayRequest payRequest = new PayRequest(PayMethodType.CARD, ConvenienceType.G25, 1000);
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        System.out.println(payResponse);

        // 취소 500원
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 500);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        System.out.println(payCancelResponse);
    }
}
