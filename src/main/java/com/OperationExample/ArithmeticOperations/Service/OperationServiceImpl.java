package com.OperationExample.ArithmeticOperations.Service;

import org.springframework.stereotype.Component;

@Component
public class OperationServiceImpl {

	int n;

	public int addition(int n1, int n2) {
		n = n1 + n2;

		return n;

	}

	public int subtraction(int n1, int n2) {
		n = n1 - n2;

		return n;
	}

	public int multiplication(int n1, int n2) {
		n = n1 * n2;

		return n;
	}

	public int division(int n1, int n2) {
		n = n1 / n2;

		return n;
	}
}
