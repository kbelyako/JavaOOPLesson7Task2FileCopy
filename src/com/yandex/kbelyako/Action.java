//Java OOP Lesson 7 Task 2
//
//2. ���������� ��������� �������������� ����������� ����� ������� � ������� ��������� �� �����.
//����������� 

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
		this.bufer = new byte[1024 * 1024];
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

	public File getIn() {
		return in;
	}

	public File getOut() {
		return out;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public synchronized void read(String text) throws IOException {

		for (; this.counter != 1;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (stop != true) {
			byteread = fis.read(bufer);
			System.out.println(text + " Have copied from input file-"
					+ byteread + " Bytes");

		}
		this.counter = 2;
		notifyAll();
	}

	public synchronized void progress(String text) {

		for (; this.counter != 2;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (stop != true) {
			curSize = curSize + byteread;
			System.out.println(text + " Bytes copied for the moment-" + curSize
					+ ", " + curSize * 100 / in.length() + "%");

		}

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
		try {
			fos.write(bufer, 0, byteread);
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (stop != true) {
			System.out.println(text + " Current size of target file-"
					+ out.length());
			if (out.length() == in.length()) {
				this.counter = 1;
				stop = true;
				notifyAll();
			} else {
				this.counter = 1;
				notifyAll();
			}

		}
	}

}
