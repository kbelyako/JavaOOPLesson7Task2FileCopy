package com.yandex.kbelyako;

public class WriteFile implements Runnable {

	private int number = 3;
	private Action ac;
	private Thread thr;

	public WriteFile(int number, Action ac) {
		super();
		this.number = number;
		this.ac = ac;
		thr = new Thread(this);
	}

	@Override
	public void run() {
		for (; !ac.isStop();) {
			ac.write(thr.getName());
		}
		System.out.println(thr.getName()+" I'm done");
	}

}
