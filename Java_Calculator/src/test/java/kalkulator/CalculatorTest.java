package kalkulator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
		// Arrange
		// sut = System Under Test
	private Calculator sut;

	@BeforeEach
	public void setUp() {
		sut = new Calculator();
	}


	//// DODAWANIE ////


	@Test
	public void testAddOne(){
		// Act
		sut.add(1);
		// Assert
		assertEquals(1, sut.getState());
	}
	@Test
	public void testAddNegative(){
		sut.setState(5);
		sut.add(-5);
		assertEquals(0, sut.getState());
	}
	@Test
	public void testAddExpression(){
		sut.setState(5);
		sut.add(5*3);
		assertEquals(20, sut.getState());
	}
	@Test
	public void testAddOverflow(){
		sut.setState(Integer.MAX_VALUE - 1);
		assertThrows(ArithmeticException.class, () -> sut.add(2));
	}
	@Test
	public void testAddNegativeOverflow(){
		sut.setState(Integer.MIN_VALUE + 1);
		assertThrows(ArithmeticException.class, () -> sut.add(-2));
	}
	/*@Test
	public void testAddOneToMax(){
		sut.setState(Integer.MAX_VALUE);
		sut.add(1);
		assertEquals(Integer.MIN_VALUE, sut.getState());
	}
	@Test
	public void testAddNegativeToMin(){
		sut.setState(Integer.MIN_VALUE);
		sut.add(-1);
		assertEquals(Integer.MAX_VALUE, sut.getState());
	} */


	////// MNOŻENIE ////


	@Test
	public void testMultOneByTwo(){
		sut.setState(1);
		sut.mult(2);
		assertEquals(2, sut.getState());
	}
	@ParameterizedTest
	@ValueSource(ints = {-1, -4, -3})
	public void testMultPosByNeg(int value){
		sut.setState(2);
		sut.mult(value);
		assertTrue((sut.getState() < 0), "Wynik mnożenia powinien być ujemny dla wartości: " + value);
	}
	@ParameterizedTest
	@ValueSource(ints = {-3, -6, -12})
	public void testMultNegByNeg(int value){
		sut.setState(-3);
		sut.mult(value);
		assertTrue(sut.getState() > 0);
	}
	@ParameterizedTest
	@ValueSource(ints = {3, -6, 10})
	public void testMultZeroByValue(int value){
		sut.setState(0);
		sut.mult(value);
        assertEquals(0, sut.getState());
	}

	/// DZIELENIE ///

	@ParameterizedTest
	@ValueSource(ints = {6 , 18, 20})
	public void testDivByGreater(int value){
		sut.setState(5);
		sut.div(value);
		assertEquals(0, sut.getState());
	}
	@Test
	public void testDivByLess(){
		sut.setState(10);
		sut.div(-2);
		assertEquals(-5, sut.getState());
	}
	@ParameterizedTest
	@ValueSource(ints = {1, -5, 10})
	public void testDivByOne(int value){
		sut.setState(value);
		sut.div(1);
		assertEquals(value, sut.getState());
	}
	@ParameterizedTest
	@ValueSource(ints = {1, -5, 10})
	public void testDivByNegative(int value){
		sut.setState(value);
		sut.div(-1);
		assertEquals(-(value), sut.getState());
	}

	@Test
	public void testDivByZero(){
		sut.setState(2);
		assertThrows(ArithmeticException.class,() -> sut.div(0));
	}

	//////// MODULO /////////

	@Test
	public void testModNegative(){
		sut.setState(-2);
		sut.mod(3);
		assertEquals(1, sut.getState());
	}
	@ParameterizedTest
	@ValueSource(ints = {5, 7, 25, 40})
	public void testLessModGreater(int state){
		sut.setState(state);
		sut.mod(45);
		assertEquals(state, sut.getState());
	}
	@ParameterizedTest
	@ValueSource(ints = {5, 15, 9})
	public void testModResultEqualsZero(int value){
		sut.setState(45);
		sut.mod(value);
		assertEquals(0, sut.getState());
	}
	@ParameterizedTest
	@ValueSource(ints = {2, 3, 6, 12})
	public void testModPositive(int value){
		sut.setState(20);
		sut.mod(value);
		assertEquals(20 % value, sut.getState());
	}
	@ParameterizedTest
	@ValueSource(ints = {20, 47, 1})
	public void testModZero(int state){
		sut.setState(state);
		assertThrows(ArithmeticException.class, () -> sut.mod(0));
	}

	///// PAMIĘĆ ////

	@Test
	public void testSaveTest(){
		sut.setState(20);
		sut.storeInMemory();
		assertEquals(sut.getState(), sut.getMemoryValue());
		assertEquals(20, sut.getState());
	}

	@Test
	public void testRecallMemory(){
		sut.setState(20);
		sut.storeInMemory();
		sut.setState(10);
		sut.recallMemory();
		assertEquals(sut.getMemoryValue(), sut.getState());
	}

	@ParameterizedTest
	@ValueSource(ints = {2, 20, 44})
	public void testAddToValueInMemoryTwice(int state){
		sut.setState(state);
		sut.addToValueInMemory();
		sut.addToValueInMemory();
		assertEquals((sut.getState()*2), sut.getMemoryValue());
		assertEquals(state, sut.getState());
	}
	@Test
	public void testAddToValueInMemoryOverflow(){
		sut.setState(Integer.MAX_VALUE);
		sut.storeInMemory();
		sut.setState(1);
		assertThrows(ArithmeticException.class, () -> sut.addToValueInMemory());

	}
	@ParameterizedTest
	@ValueSource(ints = {2, 5, 25})
	public void testMultMemory(int state){
	sut.setState(state);
	sut.storeInMemory();
	sut.multValueInMemory();
	assertEquals((state * state), sut.getMemoryValue());
	assertEquals(state, sut.getState());
	}
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 5, 10})
	public void testDivMemory(int state){
		sut.setState(state);
		sut.storeInMemory();
		sut.divValueInMemory();
		assertEquals(1, sut.getMemoryValue());
	}
	@Test
	public void testDivBy0Memory(){
		sut.setState(5);
		sut.storeInMemory();
		sut.setState(0);
		assertThrows(ArithmeticException.class, () -> sut.divValueInMemory());
	}
	@Test
	public void testModMemory(){
		sut.setState(25);
		sut.storeInMemory();
		sut.setState(14);
		sut.modValueInMemory();
		assertEquals(11, sut.getMemoryValue());

	}
	@Test
	public void testModMemoryError(){
		sut.setState(14);
		sut.storeInMemory();
		sut.setState(0);
		assertThrows(ArithmeticException.class, () -> sut.modValueInMemory());
	}
	@Test
	public void testMultipleStateOperationsThenMemory(){
		sut.setState(45);
		sut.storeInMemory();
		sut.add(45);
		sut.div(5);
		sut.mult(-3);
		sut.addToValueInMemory();
		assertEquals(-9, sut.getMemoryValue());
	}
}

