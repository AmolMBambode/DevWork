//import java.util.*;
import java.io.*;

public class stock extends Thread {
	// Perform I/O operation

	public static int maxTexp = 10;
	public static boolean stockStatus = false;
	public static boolean exchangeStatus = false;
	public static boolean cleanupStatus = false;
	
	FileOutputStream fsStock = null;
	PrintStream pStock = null;

	static long systime = System.currentTimeMillis();
	Node Y = null;
	int time = -1;
	int texp = -1;
	int qty = -1;
	int price = -1;
	String name;
	String type;
	char stockname;
	boolean partial;
	boolean shouldDo = false;
	boolean error = false;
	// objectQueue
	static queue q = new queue();

	// parses the instruction and decodes it
	public boolean info(String s) {
		// System.out.println("in info");
		boolean parsable = true;
		try {
			String[] faltu = s.split("\\s+");
			time = Integer.parseInt(faltu[0]);
			texp = Integer.parseInt(faltu[2]);
			qty = Integer.parseInt(faltu[4]);
			price = Integer.parseInt(faltu[6]);

			name = faltu[1];
			type = faltu[3];
			stockname = faltu[5].charAt(0);
			if (faltu[7].charAt(0) == 'T')
				partial = true;
			else if (faltu[7].charAt(0) == 'F')
				partial = false;
			else
				partial = false;

			if (faltu[5].length() > 1 || faltu[7].length() > 1)
				error = true;

		} catch (NumberFormatException e) {
			pStock.println("NumberFormatException - Not a number where a number should be " + s);

			parsable = false;
		}

		if (parsable && (time <= 0 || texp <= 0 || qty <= 0 || price <= 0)) {
			parsable = false;
			pStock.println("ImproperFormatException- time,texp,qty or price negative " + s);

		}

		if (error)
			pStock.println("StringLengthException- stockname or partial are not a character " + s);

		if (parsable)
			return true;
		else
			return false;
	}

	// run
	public void run() {

		try {
			fsStock = new FileOutputStream(" order1.txt ", false);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pStock = new PrintStream(fsStock);
		while (true) {

			if (Y != null) {
				if (maxTexp < (time + texp))
					maxTexp = time + texp + 2;
				q.enqueue(time, name, texp, type, qty, stockname, price,
						partial);
				pStock.println((double) (System.currentTimeMillis() - systime)
						/ 1000 + " " + time + " " + name + " " + texp + " "
						+ type + " " + qty + " " + stockname + " " + price
						+ " " + partial);
				Y = null;

			}
			try {

				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// constructor
	public stock() {
		start();

		Exchange e = new Exchange();

	}

	// performAction
	void performAction(String actionString) {
		shouldDo = info(actionString);
		Node X = new Node(actionString);
		if (shouldDo) {

			while ((System.currentTimeMillis() - systime) / 1000 < time) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			Y = X;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else;
			
	}

}
