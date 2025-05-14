package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.ConvenienceType;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.dto.PayResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();

    @Test
    void pay_success() {
        //given
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 100);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        assertEquals(100, payResponse.getPaidAmount());
    }

    @Test
    void pay_fail() {
        //given
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 1000_001);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.FAIL, payResponse.getPayResult());
        assertEquals(0, payResponse.getPaidAmount());
    }
}