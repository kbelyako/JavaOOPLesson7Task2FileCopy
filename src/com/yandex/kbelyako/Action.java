package com.yandex.kbelyako;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Action {
	private int counter;
	private boolean stop = false;
	private File in;
	private File out;
	private byte[] bufer;
	private int byteread;
	private FileOutputStream fos;
	private FileInputStream fis;
	private int curSize;

	public Action(File in, File out) {
		super();
		this.in = in;
		this.out = out;
		this.counter = 1;
		this.bufer = new byte[1024 * 512];
		this.byteread = 0;
		this.curSize = 0;
		try {
			this.fos = new FileOutputStream(out);
			this.fis = new FileInputStream(in);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	public Action() {
		super();

	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public synchronized void read(String text) throws IOException {

		for (; (byteread = fis.read(bufer)) > 0;) {

			for (; this.counter != 1;) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(text + " Have copied from input file-"
					+ byteread + " Bytes");
			// System.out.println(text + " " + byteread);
			this.counter = 2;
			notifyAll();

		}

	}

	public synchronized void progress(String text) {

		for (; this.counter != 2;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		curSize = curSize + byteread;
		// System.out.println("Current size of target file "+out.length());
		System.out.println(text + " Bytes copied for the moment-" + curSize
				+ ", " + curSize * 100 / in.length() + "%");
		// System.out.println(text);
		this.counter = 3;
		notifyAll();
	}

	public synchronized void write(String text) {

		for (; this.counter != 3;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(out.length());
		try {
			fos.write(bufer, 0, byteread);
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println(text + " Current size of target file-"
				+ out.length());
		
		this.counter = 1;
		notifyAll();
	}

}
