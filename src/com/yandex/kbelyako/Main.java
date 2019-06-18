package com.yandex.kbelyako;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		File copySource = new File("CopySource");
		File copyTarget = new File("CopyTarget");
		copySource.mkdirs();
		copyTarget.mkdirs();
		   Action ac1=new Action(1);
			ReadFile rf=new     ReadFile(1,ac1);
			Progress pr=new  Progress(2,ac1);
			WriteFile wf=new WriteFile(3,ac1);
			
			
			Thread readFileThread=new Thread(rf);
			Thread progressThread=new Thread(pr);
			Thread writeFileThread=new Thread(wf);
			
			readFileThread.start();
			progressThread.start();
			writeFileThread.start();

	}

}
