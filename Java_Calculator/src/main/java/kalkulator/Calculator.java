package kalkulator;

public class Calculator {
	private int state = 0;

	public void add(int value){ //warunek + throw
		state += value;
	}

	public void mult(int value){
		state *= value;
	}

	public void div(int value){
		state /= value;
	}

	public void mod(int value){ // -2 % 3 = 1 zmienić definicje modulo na matematyczna
		state %= value;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
