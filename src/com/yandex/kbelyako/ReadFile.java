package com.yandex.kbelyako;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFile implements Runnable {

	private int number = 1;
	private Action ac;
	private Thread thr;

	public ReadFile(int number, Action ac) {
		super();
		this.number = number;
		this.ac = ac;
		thr = new Thread(this);
	}

	@Override
	public void run() {
		while (ac.isStop()==false) {
			try {
				ac.read(thr.getName());
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		//ac.setStop(true);
System.out.println(thr.getName()+" I'm done");
	}

}
