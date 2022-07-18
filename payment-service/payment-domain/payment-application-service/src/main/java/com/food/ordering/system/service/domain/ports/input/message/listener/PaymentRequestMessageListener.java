package com.food.ordering.system.service.domain.ports.input.message.listener;

import com.food.ordering.system.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}
