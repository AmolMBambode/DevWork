public class sellElement {
	public sellElement nextElement;
	int time = -1;
	int texp = -1;
	int qtyTotal = -1;
	int qtyAvailable = -1;
	int price = -1;
	String name;
	String type;
	char stockname;
	boolean partial;

	public sellElement(int Time, String Name, int Texp, String Type,
			int QtyTotal, int QtyAvailable, char StockName, int Price,
			boolean Partial) {
		nextElement = null;
		time = Time;
		name = Name;
		texp = Texp;
		qtyTotal = QtyTotal;
		qtyAvailable = QtyAvailable;
		stockname = StockName;
		price = Price;
		partial = Partial;
		type = Type;
	}

	public void setSellElement(int Time, String Name, int Texp, String Type,
			int QtyTotal, int QtyAvailable, char StockName, int Price,
			boolean Partial) {
		nextElement = null;
		time = Time;
		name = Name;
		texp = Texp;
		qtyTotal = QtyTotal;
		qtyAvailable = QtyAvailable;
		stockname = StockName;
		price = Price;
		partial = Partial;
		type = Type;
	}

	public sellElement getNextElement() {
		return nextElement;
	}

	public void setNextElement(sellElement nextValue) {
		nextElement = nextValue;
	}

	public sellElement getSellData() {
		return this;
	}
}
