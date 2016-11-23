package simRace;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class SimRace {

	public static final int CAR_AMOUNT = 5; //Anzahl der Fahrzeuge im Rennen
	public static final int ROUNDS = 50;  //Runden die zu fahren sind
	public static boolean iSACCIDENT = false; //Anzeige ob der Unfall sich bereits erreignet hat

	List<Car> cars = new ArrayList<Car>();

	public void startRace() {
		for (int i = 1; i <= CAR_AMOUNT; i++) { //Anzahl der Fahrzeuge erzeugen und in einer Liste sammeln
			cars.add(new Car("Auto" + i));
		}
		for (Car c : cars) { //Fahrzeuge starten
			c.start(); 
		}

		Accident unfall = new Accident();
		unfall.start(); //Unfall starten

		for (Car c : cars) { //Fahrzeug-Threads joinen damit der main Thread wartet bis alle Fahrzeuge fertig sind
			try {
				c.join();
			} catch (InterruptedException e) {
				c.interrupt();
			}

		}
	}

	public void ergebnis() {
		if (!iSACCIDENT) { //Wenn sich kein Unfall erreignet hat Fahrzeugliste nach Plätzen sortieren (mithilfe eines Comparators)
			Collections.sort(cars, new Comparator<Car>() {
				@Override
				public int compare(Car o1, Car o2) {
					if (o1.getGesamtFahrzeit() < o2.getGesamtFahrzeit())
						return -1;
					else if (o1.getGesamtFahrzeit() > o2.getGesamtFahrzeit())
						return 1;
					else
						return 0;
				}
			});

			for (int i = 1; i <= CAR_AMOUNT; i++) { // Fahrzeuge der Platzierung nach ausgeben
				System.out
						.println(i + ". Platz: " + cars.get(i - 1).toString());
			}

		}
		else{
			System.out.println("Ein Unfall hat sich erreignet und das Rennen wurde gestoppt!"); //Anzeige falls sich Unfall erreignete
		}
		iSACCIDENT = false; 
	}

	public static void main(String[] args) {
		SimRace race = new SimRace();
		race.startRace();
		race.ergebnis();

	}

}
