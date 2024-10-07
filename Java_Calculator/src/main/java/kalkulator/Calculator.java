package kalkulator;

public class Calculator {
	private int state = 0;

	public void add(int value){
		state += value;
	} //throws

	public void mult(int value){
		state *= value;
	}

	public void div(int value){
		state /= value;
	}


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
