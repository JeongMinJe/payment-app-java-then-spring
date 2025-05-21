package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConveniencePayService {

    // 📌 변경 전: 필요한 어댑터와 할인 인터페이스 구현체를 직접 내부에서 생성함.
    // private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    // private final CardAdapter cardAdapter = new CardAdapter();
    // private final DiscountInterface discountInterface = new DiscountByPayMethod(); // 예시 (이전 코드에서는 DiscountByPayMethod를 사용)

    // 📌 변경 후: 결제 수단 타입별로 실제 결제 처리 객체(PaymentInterface)를 저장하는 Map을 사용.
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap = new HashMap<>();
    private final DiscountInterface discountInterface;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet,
                                 DiscountInterface discountInterface) {

        paymentInterfaceSet.forEach(
                paymentInterface -> paymentInterfaceMap.put(
                            paymentInterface.getPayMethodType(),
                            paymentInterface
                )
        );

        this.discountInterface = discountInterface;
    }

    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());

        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult paymentResult = paymentInterface.payment(discountedAmount);

        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        return new PayResponse(PayResult.SUCCESS, discountedAmount);

    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

        CancelPaymentResult cancelPaymentResult = paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());

        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_fAIL, 0);
        }

        // Success Case
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());

    }
}
