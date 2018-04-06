public class buyElement {
	buyElement nextElement;
	int time = -1;
	int texp = -1;
	int qtyTotal = -1;
	int qtyAvailable = -1;
	int price = -1;
	String name;
	String type;
	char stockname;
	boolean partial;

	public buyElement(int Time, String Name, int Texp, String Type,
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

	public void setBuyElement(int Time, String Name, int Texp, String Type,
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

	public buyElement getNextElement() {
		return nextElement;
	}

	public void setNextElement(buyElement nextValue) {
		nextElement = nextValue;
	}

	public buyElement getBuyData() {
		return this;
	}
}
