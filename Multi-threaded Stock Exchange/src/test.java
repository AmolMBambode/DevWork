import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class test extends Thread {
	// Thread wrapper class

	FileOutputStream fs = null;
	PrintStream p = null;

	test() {
		start();

	}

	public void run() {

		try {
			fs = new FileOutputStream("cleanup1.txt", false);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		p = new PrintStream(fs);
		buyElement be = null;
		sellElement se = null;
		// int bc=0,sc=0;
		while (true) {
			// bc = Exchange.bl.getBuyCounter();
			// sc = Exchange.sl.getSellCounter();
			be = Exchange.bl.deleteBuy();
			if (be != null) {
				p.println((double) (System.currentTimeMillis() - stock.systime)
						/ 1000 + " " + be.time + " " + be.name + " " + be.texp
						+ " " + be.type + " " + be.qtyTotal + " "
						+ be.stockname + " " + be.price + " " + be.partial);

			}

			se = Exchange.sl.deleteSell();
			if (se != null) {
				p.println((double) (System.currentTimeMillis() - stock.systime)
						/ 1000 + " " + se.time + " " + se.name + " " + se.texp
						+ " " + se.type + " " + se.qtyTotal + " "
						+ se.stockname + " " + se.price + " " + se.partial);

			}
			be = null;
			se = null;

			// if(Exchange.bl.getBuyCounter() != bc ||
			// Exchange.sl.getSellCounter() != sc){
			// continue;
			// }
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(stock.maxTexp +"  "+ (double) (System.currentTimeMillis() - stock.systime) / 1000);
			
			if (stock.maxTexp < (double) (System.currentTimeMillis() - stock.systime) / 1000) {
				System.exit(0);
			}

		}
	}
}
