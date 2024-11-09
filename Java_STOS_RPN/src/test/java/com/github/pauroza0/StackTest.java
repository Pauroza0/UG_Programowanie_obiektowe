package com.github.pauroza0;

import com.github.pauroza0.Stack;
import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTest {
	Stack<String> stack = new Stack<>();
	@Test
	public void testPopAfterPush() {
		stack.push("napis");
		String result = stack.pop();

		assertEquals("napis", result);
	}
	@Test
	public void testPopAfterPushTwice_ShouldReturnSecondValue(){
		stack.push("e1");
		stack.push("e2");
		String result = stack.pop();

		assertEquals("e2", result);
	}
	@Test
	public void testPopTwiceAfterPushTwice_ShouldReturnFirstValue(){
		stack.push("e1");
		stack.push("e2");

		stack.pop();
		String result = stack.pop();

		assertEquals("e1", result);
	}
}
