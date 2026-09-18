package com.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootPracticeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPracticeApplication.class, args);
	}
	
	
	//traditional java approach
	//created a class payment service and to use its method create its object
	//OOPs java programming
//	private PaymentService paymentService = new PaymentService();
	
	//as we have declared it as component the object/bean (new keyword we create object) that is created
	//but how can we use it?
	//using dependency injection
	
	//@Autowired  //2.field injection
	//private PaymentService paymentService;
	
	//final you cant change it -> secure
	private final PaymentService paymentService;
	
	//1.constructor injection
	//now when u run this main file as we create the main object constructore is initialzied that make dependent on this
	//service so it injects the dependency means give it the bean
	
	//3. The payment service shows error as we have 2 beans one for stripe and razorpay
	//so we need to tell which one to use but this is good for loose coupling
	//u are just saying just do payment and the type of payement is present in application.properties file which
	//can be configured by you
	public SpringbootPracticeApplication(PaymentService paymentService) {
		this.paymentService = paymentService; //initiazlise this varibale with the bean
	}
	
	//this runs after spring runs..implement command line runner
	public void run(String... args) throws Exception{
		String payment = paymentService.pay();
		System.out.println("Payment status " + payment);
		
	}

}
