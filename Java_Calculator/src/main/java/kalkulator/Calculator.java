package kalkulator;

public class Calculator {
	private int state = 0;
	private int memory = 0;

	public void add(int value){ //warunek + throw
		if((value > 0 && state > Integer.MAX_VALUE - value) || (value < 0 && state < Integer.MIN_VALUE - value)){
			throw new ArithmeticException("Przepełnienie");
		}
		state +=value;
	}

	public void mult(int value){
		state *= value;
	}

	public void div(int value){
		if(value == 0){
			throw new ArithmeticException("Dzielenie przez 0");
		}
		state /= value;
	}

	public void mod(int value){ ////////////przez 0
		if(value == 0){
			throw new ArithmeticException("Modulo przez 0");
		}
		state = ((state % value) + value) % value;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/// STAN ////
	public void storeInMemory(){
		memory = state;
	}
	public int getMemoryValue(){
		return memory;
	}
	public void clearMemory(){
		memory = 0;
	}
	public void recallMemory(){
		state = memory;
	}
	public void addToValueInMemory(){
		int value = state;
		if((state > 0 && memory > Integer.MAX_VALUE - state) || (state < 0 && memory < Integer.MIN_VALUE - state)){
			throw new ArithmeticException("Przepełnienie");
		}
		memory += state;
	}
	public void multValueInMemory(){
		memory *= state;
	}
	public void divValueInMemory(){
		if(state == 0){
			throw new ArithmeticException("Dzielenie przez 0");
		}
		memory /= state;
	}
	public void modValueInMemory(){
		if(state == 0){
			throw new ArithmeticException("Modulo przez 0");
		}
		memory = ((memory % state) + state) % state;
	}

}
