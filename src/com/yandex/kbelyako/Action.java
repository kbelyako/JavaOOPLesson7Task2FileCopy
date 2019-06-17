package com.yandex.kbelyako;

public class Action {
	private int counter;
	
	
	public Action(int counter) {
		super();
		this.counter = counter;
	}
	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}
	//this.counter=1;

	public synchronized void copy(int number) {
		
	//	System.out.println("initial counter="+counter);
		for (; this.counter!=number;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(number);
		this.counter = this.counter +1;		
		notifyAll();
	}


}
