package simRace;
import java.util.Random;


public class Car extends Thread{
	
//Attribute
private String carname;
private int gesamtFahrzeit = 0;
private int gefahreneRunden = 0;


//Konstruktor
	public Car(String carname){
		this.carname = carname;
	}
	

//Getter
	public String getCarName(){
		return carname;
	}
	
	int getGesamtFahrzeit(){
		return gesamtFahrzeit;
	}
	
	int getgefahreneRunden(){
		return gefahreneRunden;
	}
	
	public String toString(){
		return carname+" Zeit: "+gesamtFahrzeit;
	}
	
	
	public void run(){
// fährt so lange weiter bis er interrupted wird (z.b. durch Accident) oder die Rundenzahl erreicht hat
			while (gefahreneRunden!=SimRace.ROUNDS && !isInterrupted() && !SimRace.iSACCIDENT){
		Random rnd = new Random();
		int rundenZeit = rnd.nextInt(100);  // Randomdauer zwischen 0-100ms für eine Runde
		try {
		Thread.sleep(rundenZeit); // Wartet die Randomrundendauer
		} catch (InterruptedException e) {

	        System.err.println("Car wurde durch Interrupt geweckt!");
	        this.interrupt(); // Interrupt muss neu gesetzt werden nachdem es gecatcht wird
	      }
		gesamtFahrzeit += rundenZeit; // erhöhen der Fahrzeit und Rundenanzahl
		gefahreneRunden++;
	}
	}
}