package kalkulator;


import org.junit.jupiter.api.*;
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

    @Nested
    class AddOperationTests{
		@Test
		public void testAddOne(){
		// Act
			sut.add(1);
		// Assert
			assertEquals(1, sut.getState());
		}
		@Test
		public void testAddNegativeNumber(){
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
		public void testAddOverflow_ShouldThrowArithmeticException(){
			sut.setState(Integer.MAX_VALUE - 1);

			assertThrows(ArithmeticException.class, () -> sut.add(2));
		}
		@Test
		public void testAddNegativeToMin_ShouldThrowArithmeticException(){
			sut.setState(Integer.MIN_VALUE + 1);

			assertThrows(ArithmeticException.class, () -> sut.add(-2));
		}
		@Test
		@Disabled
		public void testAddOneToMax_ShouldOverflow(){
			sut.setState(Integer.MAX_VALUE);

			sut.add(1);

			assertEquals(Integer.MIN_VALUE, sut.getState());
		}
		@Test
		@Disabled
		public void testAddNegativeToMin_ShouldOverflow(){
			sut.setState(Integer.MIN_VALUE);

			sut.add(-1);

			assertEquals(Integer.MAX_VALUE, sut.getState());
		}

	}

	@Nested
	class MultiplyOperationTests{
		@Test
		public void testMultPositiveNumbers_ShouldReturnCorrectValue(){
			sut.setState(1);

			sut.mult(2);

			assertEquals(2, sut.getState());
		}
		@ParameterizedTest
		@ValueSource(ints = {-1, -4, -3})
		public void testMultPositiveByNegative_ShouldReturnNegative(int value){
			sut.setState(2);
			sut.mult(value);
			assertTrue(sut.getState() < 0);
		}
		@ParameterizedTest
		@ValueSource(ints = {-3, -6, -12})
		public void testMultNegativeByNegative_ShouldReturnPositive(int value){
			sut.setState(-3);
			sut.mult(value);
			assertTrue(sut.getState() > 0);
		}
		@ParameterizedTest
		@ValueSource(ints = {3, -6, 10})
		public void testMultZeroByValue_ShouldReturnZero(int value){
			sut.setState(0);
			sut.mult(value);
        	assertEquals(0, sut.getState());
		}

	}


	@Nested
	class DivideOperationTests{
		@ParameterizedTest
		@ValueSource(ints = {6 , 18, 20})
		public void testDivByGreaterNumber_ShouldReturnZero(int value){
			sut.setState(5);
			sut.div(value);
			assertEquals(0, sut.getState());
		}
		@Test
		public void testDivByLess_ShouldReturnCorrectValue(){
			sut.setState(10);
			sut.div(-2);
			assertEquals(-5, sut.getState());
		}
		@ParameterizedTest
		@ValueSource(ints = {1, -5, 10})
		public void testDivByOne_ShouldReturnUnchangedState(int value){
			sut.setState(value);
			sut.div(1);
			assertEquals(value, sut.getState());
		}
		@ParameterizedTest
		@ValueSource(ints = {1, -5, 10})
		public void testDivByNegativeOne_ShouldReturnOppositeNumber(int value){
			sut.setState(value);
			sut.div(-1);
			assertEquals(-(value), sut.getState());
		}

		@Test
		public void testDivByZero_ShouldThrowArithmeticException(){
			sut.setState(2);
			assertThrows(ArithmeticException.class,() -> sut.div(0));
		}

	}

	@Nested
	class ModuloOperationTests{
		@Test
		public void testModNegativeNumber_ShouldReturnNonNegative(){
			sut.setState(-2);
			sut.mod(3);
			assertEquals(1, sut.getState());
		}
		@ParameterizedTest
		@ValueSource(ints = {5, 7, 25, 40})
		public void testModGreaterPositive_ShouldReturnUnchangedState(int state){
			sut.setState(state);
			sut.mod(45);
			assertEquals(state, sut.getState());
		}
		@ParameterizedTest
		@ValueSource(ints = {5, 15, 9})
		public void testModDivider_ShouldReturnZero(int value){
			sut.setState(45);
			sut.mod(value);
			assertEquals(0, sut.getState());
		}
		@ParameterizedTest
		@ValueSource(ints = {2, 3, 6, 12})
		public void testModPositiveNumbers_ShouldReturnCorrectValue(int value){
			sut.setState(20);
			sut.mod(value);
			assertEquals(20 % value, sut.getState());
		}
		@ParameterizedTest
		@ValueSource(ints = {20, 47, 1})
		public void testModZero_ShouldThrowArithmeticException(int state){
			sut.setState(state);
			assertThrows(ArithmeticException.class, () -> sut.mod(0));
		}
	}

	@Nested
	class MemoryOperationsTests{
		@Test
		public void testStoreInMemoryTest_MemoryShouldEqualState(){
			sut.setState(20);

			sut.storeInMemory();

			assertEquals(sut.getState(), sut.getMemoryValue());
			assertEquals(20, sut.getState());
		}

		@Test
		public void testRecallMemory_StateShouldEqualMemory(){
			sut.setState(20);
			sut.storeInMemory();
			sut.setState(10);

			sut.recallMemory();

			assertEquals(sut.getMemoryValue(), sut.getState());
		}

		@ParameterizedTest
		@ValueSource(ints = {2, 20, 44})
		public void testAddToValueInMemoryTwice_ShouldReturnCorrectValue(int state){
			sut.setState(state);

			sut.addToValueInMemory();
			sut.addToValueInMemory();

			assertEquals((sut.getState()*2), sut.getMemoryValue());
			assertEquals(state, sut.getState());
		}
		@Test
		public void testAddToValueInMemoryOverflow_ShouldThrowArithmeticException(){
			sut.setState(Integer.MAX_VALUE);
			sut.storeInMemory();
			sut.setState(1);

			assertThrows(ArithmeticException.class, () -> sut.addToValueInMemory());
		}
		@ParameterizedTest
		@ValueSource(ints = {2, 5, 25})
		public void testMultMemory_ShouldReturnCorrectValueAndNotChangeState(int state){
			sut.setState(state);
			sut.storeInMemory();

			sut.multValueInMemory();

			assertEquals((state * state), sut.getMemoryValue());
			assertEquals(state, sut.getState());
		}
		@ParameterizedTest
		@ValueSource(ints = {1, 2, 5, 10})
		public void testDivMemory_ShouldReturnOne(int state){
			sut.setState(state);
			sut.storeInMemory();

			sut.divValueInMemory();

			assertEquals(1, sut.getMemoryValue());
		}
		@Test
		public void testDivBy0Memory_ShouldThrowArithmeticException(){
			sut.setState(5);
			sut.storeInMemory();

			sut.setState(0);

			assertThrows(ArithmeticException.class, () -> sut.divValueInMemory());
		}
		@Test
		public void testModMemory_ShouldReturnCorrectValue(){
			sut.setState(25);
			sut.storeInMemory();
			sut.setState(14);

			sut.modValueInMemory();

			assertEquals(11, sut.getMemoryValue());
		}
		@Test
		public void testModMemoryError_ShouldThrowArithmeticException(){
			sut.setState(14);
			sut.storeInMemory();
			sut.setState(0);

			assertThrows(ArithmeticException.class, () -> sut.modValueInMemory());
		}
		@Test
		public void testMultipleStateOperationsThenMemory_ShouldReturnCorrectValue(){
			sut.setState(45);
			sut.storeInMemory();

			sut.add(45);
			sut.div(5);
			sut.mult(-3);

			sut.addToValueInMemory();

			assertEquals(-9, sut.getMemoryValue());
		}
	}
}

