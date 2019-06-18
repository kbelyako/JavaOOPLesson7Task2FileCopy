package com.yandex.kbelyako;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Action {
	private int counter;
	private boolean stop = false;
	
	
	public Action(int counter) {
		super();
		this.counter = counter;
	}
	public Action() {
		super();
		
	}
	
	
	//this.counter=1;
	
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}


	public synchronized void read (String text, File in) throws IOException {
		
	//	System.out.println("initial counter="+counter);
		for (; this.counter!=1;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(text);

		byte[] bufer = new byte[1024 * 1024 * 8];
		int byteread = 0;
		FileInputStream fis;
		try {
			fis = new FileInputStream(in);
			byteread = fis.read(bufer);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		;
		
		this.counter = 2;		
		notifyAll();
	}
	
	public synchronized void copy(String text) {
//		this.counter=this.counter+1;
	//	System.out.println("initial counter="+counter);
		for (; this.counter!=1;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(text);
		this.counter = 2;		
		notifyAll();
	}
	
	public synchronized void copy1(String text) {
//		this.counter=this.counter+1;
	//	System.out.println("initial counter="+counter);
		for (; this.counter!=2;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(text);
		this.counter = 3;		
		notifyAll();
	}
	
	public synchronized void copy2(String text) {
	//	this.counter=this.counter+1;
	//	System.out.println("initial counter="+counter);
		for (; this.counter!=3;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(text);
		this.counter = 1;		
		notifyAll();
	}


}
