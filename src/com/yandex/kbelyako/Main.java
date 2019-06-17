package com.yandex.kbelyako;

public class Main {

	public static void main(String[] args) {
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
