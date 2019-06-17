package com.yandex.kbelyako;

public class WriteFile implements Runnable {
	
	private int number=3;
	private Action ac;
	
	

	public WriteFile(int number, Action ac) {
		super();
		this.number = number;
		this.ac = ac;
	}



	@Override
	public void run() {
		
	ac.copy(this.number);	
	}
	

}

