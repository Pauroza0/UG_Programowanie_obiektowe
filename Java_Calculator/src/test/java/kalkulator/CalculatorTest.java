package kalkulator;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorTest {
		// Arrange
		// sut = System Under Test
	private Calculator sut;

	@Before public void setUp() {
		sut = new Calculator();
	}

	@Test
	public void testAddOne(){ //ujemne
		// Act
		sut.add(1);
		// Assert
		assertEquals("0+1 = 1", 1, sut.getState());
	}
	@Test
	public void testAddNegative(){
		sut.setState(5);
		sut.add(-5);
		assertEquals("5-5 = 0", 0, sut.getState());
	}
	@Test
	public void testAddExpression(){
		sut.setState(5);
	}

	@Test
	public void testMultOneByTwo(){
		sut.setState(1);
		sut.mult(2);
		assertEquals("1*2 = 2", 2, sut.getState());
	}
	@Test
	public void testAddOneToMax(){ //throws
		sut.setState(Integer.MAX_VALUE);
		sut.add(1);
		assertEquals(Integer.MIN_VALUE, sut.getState());
	}
	@Test
	public void testDivByLess(){
		sut.setState(1);
		sut.div(2);
		assertEquals("1/2 = 0", 0, sut.getState());
	}
	@Test
	public void testDivByMore(){
		sut.setState(2);
		sut.div(1);
		assertEquals("2/1 = 2", 2, sut.getState());
	}
	@Test
	public void testDivByZero(){
		sut.setState(2);
		assertThrows(ArithmeticException.class, () -> sut.div(0));
	}
}
