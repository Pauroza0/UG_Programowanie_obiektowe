package com.github.pauroza0;

import java.util.Arrays;

public class Stack {
	private String[] elements;
	private int top;

	public Stack(){
		this.elements = new String[0];
        this.top = -1;
	}

	public void push(String element){
		elements = Arrays.copyOf(elements, top + 2);
		top++;
		elements[top] = element;
	}
	public String pop(){
		if (top < 0) {
			throw new IllegalStateException("Stos jest pusty");
		}
		String value = elements[top];
		elements = Arrays.copyOf(elements, top--);
		return value;
	}

	public String peek(){
		if (top < 0) {
			throw new IllegalStateException("Stos jest pusty");
		}
		return elements[top];
	}

	public int getTop() {
		return top;
	}
}
