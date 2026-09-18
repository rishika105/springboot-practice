package com.project;

import org.springframework.stereotype.Component;


//to say spring to create bean/object
@Component
public interface PaymentService {
	public String pay();
}
