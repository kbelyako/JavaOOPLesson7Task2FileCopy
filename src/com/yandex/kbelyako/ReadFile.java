package com.yandex.kbelyako;

public class ReadFile implements Runnable {
	
	private int number=1;
	private Action ac;

	public ReadFile(int number, Action ac) {
		super();
		this.number = number;
		this.ac = ac;
	}
	
	

	@Override
	public void run() {
		
	ac.copy(this.number);	
	}
	

}
