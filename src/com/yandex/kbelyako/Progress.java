package com.yandex.kbelyako;

public class Progress implements Runnable {
	
	private int number=2;
	private Action ac;
	
	

	public Progress(int number, Action ac) {
		super();
		this.number = number;
		this.ac = ac;
	}



	@Override
	public void run() {
		
	ac.copy(this.number);	
	}
	

}

