package com.project;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Component
//@RestController
//@Service
//@Repository
//all of the below annotations are also component only 
//we can use that also for creating bean
//it all tells spring to handle the beans by it and not user handles it
//** this is conditional creatiion of beans
//if value is razorpay so only create bean so as to not create multiple bean for same interface service as payemnt service

//we can also give properties from the system
//like in environment variables
//like if we have payment.provider the same we can use in PAYMENT_PROVIDER it will work
//no need to explicity provide that name...because application properties has that 
//and when we use both of that the environment variable one would work
//JUST ADD PAYMENT_PROVIDER = razorpay when u have stripe app.prop. and dont do anything else
//it will work for razpay
@ConditionalOnProperty(name = "payment.provider", havingValue = "razorpay")
public class RazorpayPaymentService implements PaymentService {
	
	@Override  //not compulsory to use
	public String pay() {
		System.out.println("Payment done!");
		return "Paying through Razorpay";
	}
}
