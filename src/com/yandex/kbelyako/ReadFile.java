package com.yandex.kbelyako;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFile implements Runnable {
	
	private int number=1;
	private Action ac;
	private Thread thr;

	public ReadFile(int number, Action ac) {
		super();
		this.number = number;
		this.ac = ac;
		thr = new Thread(this);
	}
	
	public static void copyFileCommon(File in, File out) throws IOException {
		byte[] bufer = new byte[1024 * 1024 * 8];
		int byteread = 0;
		try (FileInputStream fis = new FileInputStream(in);
				FileOutputStream fos = new FileOutputStream(out)) {
			for (; (byteread = fis.read(bufer)) > 0;) {
				fos.write(bufer, 0, byteread);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public void run() {
		File in = new File("CopySource/1.pdf");;
		byte[] bufer = new byte[1024 * 1024 * 8];
		int byteread = 0;
		try (FileInputStream fis = new FileInputStream(in);
				) {
			for (; (byteread = fis.read(bufer)) > 0;) {
				//ac.copy
				ac.copy(thr.getName());	
			}
		} catch (IOException e) {
			 e.printStackTrace();;
		}
		
//	for (int i = 0; i < 5; i++) {
//		ac.copy(thr.getName());	
//	}	
	
	ac.setStop(true);
		
	}
	

}
