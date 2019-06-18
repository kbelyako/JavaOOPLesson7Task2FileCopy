package com.yandex.kbelyako;

public class Progress implements Runnable {

	private int number = 2;
	private Action ac;
	private Thread thr;

	public Progress(int number, Action ac) {
		super();
		this.number = number;
		this.ac = ac;
		thr = new Thread(this);
	}

	@Override
	public void run() {
		for (; !ac.isStop();) {
			ac.progress(thr.getName());
		}

	}

}
