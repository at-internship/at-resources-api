package com.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.resources.model.*;
import com.resources.service.ResourcesService;

@SpringBootApplication
public class AtResourcesApi2Application {

	public static void main(String[] args) {

		json MyObject = new json();
		MyObject = MyObject.createDummy();

		ResourcesService myService = new ResourcesService();

		Sprint MyObject2 = myService.JSONtoObject(MyObject);
		json MyObject3 = myService.ObjectToJSON(MyObject2);



		SpringApplication.run(AtResourcesApi2Application.class, args);

	}


}
