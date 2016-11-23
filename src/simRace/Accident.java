package simRace;

import java.util.Random;

public class Accident extends Thread {

	public void run(){
		Random rnd = new Random();
		try {
			Thread.sleep(rnd.nextInt(4000)); // Zeit die Accident schläft (also sich noch nicht erreignet hat)
		} catch (InterruptedException e) {
		}
SimRace.iSACCIDENT = true;
		
	}
}
