package com.OperationExample.ArithmeticOperations.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OperationExample.ArithmeticOperations.Service.OperationServiceImpl;

@RestController
@RequestMapping("/arith/")
public class ArithmeticController {

	int n;

	@Value("${filepath}")
	private String filePath;
	
	
	@Autowired
	OperationServiceImpl operationSer;

	@RequestMapping(value = "operation", method = RequestMethod.GET)
	public ResponseEntity<?> operationMethod(@RequestParam Map<String, String> requestParam) throws Exception {
		
		int n=0;
		try {
//			String operation = " ";
//			String n1 = " ";
//			String n2 = " ";
			System.out.println(requestParam);

			int num1 = Integer.parseInt(requestParam.get("n1"));
			int num2 = Integer.parseInt(requestParam.get("n2"));

			switch (requestParam.get("operation")) {
			case "add":
				n = operationSer.addition(num1, num2);
				break;
			case "sub":
				n = operationSer.subtraction(num1, num2);
				break;
			case "mul":
				n = operationSer.multiplication(num1, num2);
				break;
			case "div":
				n = operationSer.division(num1, num2);
				break;
			}
			requestParam.put("Status", "Success");
			requestParam.put("Result", n + "");
		

		} catch (Exception e) {
			System.out.println("Invalid input");
			System.out.println((e.getMessage()));
			
			requestParam.put("Status", e.getMessage());
			
		}
		
		return new ResponseEntity<>(requestParam, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "load", method = RequestMethod.GET)
	public ResponseEntity<?> loadProperty(@RequestParam Map<String, String> requestParam) throws Exception {
		System.out.println("inside load");
		Properties prop = new Properties();	

		prop.put("Id", "101");
		prop.put("Name", "Joy");
		prop.put("Designation", "Software Developer");

		FileOutputStream out = new FileOutputStream("D:\\FileOperations\\joyDetails.properties");
		prop.store(out, "Details of Joy");
		
		try {
//			File f = new File("D:\\FileOperations\\propFile.properties");		
			FileInputStream fin = new FileInputStream(filePath);
			prop.load(fin);			
			System.out.println("file readed successfully");

		} catch (Exception e) {
			System.out.println((e));		
		}
		
		return new ResponseEntity<>(prop , HttpStatus.OK);
	}

}
