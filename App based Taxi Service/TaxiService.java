import java.util.LinkedList;
import java.util.List;

public class TaxiService /* extends Thread */{

	Graph g = new Graph();
	Taxi taxi = new Taxi();

	// long systime = System.currentTimeMillis();
	// long currentTime;
	public TaxiService() {
		// this.start();

	}

	void consumerCall(Place src, Place dst, int t) {
		g.computePaths(src);
		List ll = new LinkedList();
		int minTime = -1;
		TaxiNode preferredTaxi = null;
		System.out.println("Available taxis:");
		for (int i = 0; i < taxi.allTaxies.size(); i++) {
			TaxiNode tx = (TaxiNode) taxi.allTaxies.get(i);
			if (tx.checkAvailability(t)) {
				if (minTime == -1 || minTime > tx.currentPlace.minDistance) {
					minTime = tx.currentPlace.minDistance;
					preferredTaxi = tx;
				}
				System.out.print("Path of " + tx.name + ": ");
				ll = g.getShortestPathTo(tx.currentPlace);
				for (int j = ll.size() - 1; j > 0; j--) {
					System.out.print(((Place) ll.get(j)).location + ", ");
				}
				ll.clear();
				System.out.println(src.location + ". Time taken is "
						+ tx.currentPlace.minDistance + " units");
			}
		}
		System.out.println("** Chose " + preferredTaxi.name
				+ " to service the customer request ***");
		System.out.print("Path of customer: ");
		ll = g.getShortestPathTo(dst);
		for (int j = 0; j < ll.size(); j++) {
			System.out.print(((Place) ll.get(j)).location);
			if (j != ll.size() - 1)
				System.out.print(", ");
		}
		System.out.print(". time taken is " + (dst.minDistance + minTime)
				+ " units");
		makeTaxiBusy(preferredTaxi, t, t + dst.minDistance + minTime, dst);
	}

	void makeTaxiBusy(TaxiNode taxi, int t, int total, Place dst) {
		busyTime bt = new busyTime(t, total);
		taxi.time.add(bt);
		taxi.currentPlace = dst;
	}

	void printAllTaxi(int t) {
		System.out.println("Available taxis:");
		for (int i = 0; i < taxi.allTaxies.size(); i++) {
			TaxiNode tx = (TaxiNode) taxi.allTaxies.get(i);

			if (tx.checkAvailability(t)) {
				System.out.println(tx.name + ": " + tx.currentPlace.location);
			}
		}
	}

	/*
	 * public void run(){ currentTime = System.currentTimeMillis(); try {
	 * Thread.sleep(1000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
	public void performAction(String actionMessage) {
		System.out.println("\naction to be performed: " + actionMessage + "\n");
		String[] arr = actionMessage.split(" ");

		if (arr[0].equals("edge") && arr.length == 4) {

			int t = 0;

			try {
				t = Integer.parseInt(arr[3]);
			} catch (NumberFormatException n) {
				System.out.println("invalid format");
			}
			if (t < 0)
				return;
			try {
				g.addRoad(t, arr[1], arr[2]);
			} catch (roadAlreadyExistException e) {
				System.out.println("Road already exist");
			}
			// g.printGraph();
		} else if (arr[0].equals("taxi") && arr.length == 3) {
			Place p = g.hasPlace(arr[2]);
			if (p != null) {
				try {
					taxi.addTaxi(arr[1], p);
				} catch (taxiAlreadyExistException e) {
					System.out.println("taxi already exist");
				}
			} else
				System.out.println("no such place for taxi to exist");
		} else if (arr[0].equals("customer") && arr.length == 4) {

			Place src = g.hasPlace(arr[1]);
			Place dst = g.hasPlace(arr[2]);

			if (src != null && dst != null) {

				int t = 0;
				try {
					t = Integer.parseInt(arr[3]);
				} catch (NumberFormatException n) {
					System.out.println("invalid format");
				}
				if (t < 0)
					return;
				consumerCall(src, dst, t);

			} else
				System.out.println("no such places");
		}

		else if (arr[0].equals("printTaxiPosition") && arr.length == 2) {
			int t = 0;
			try {
				t = Integer.parseInt(arr[1]);
			} catch (NumberFormatException n) {
				System.out.println("invalid format");
			}
			if (t < 0)
				return;
			printAllTaxi(t);
		}

		else
			System.out.println("invalid command");
	}
}
