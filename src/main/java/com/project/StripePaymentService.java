package com.project;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "stripe")
public class StripePaymentService implements PaymentService {
   
	@Override
	public String pay() {
		System.out.println("Payment done!");
		return "Paying through Stripe";
	}


}
