public class Data {
	Data next;
	int time = -1;
	int texp = -1;
	int qty = -1;
	int price = -1;
	String name;
	String type;
	char stockname;
	boolean partial;

	public Data(int Time, String Name, int Texp, String Type, int Qty,
			char StockName, int Price, boolean Partial) {
		next = null;
		time = Time;
		name = Name;
		texp = Texp;
		qty = Qty;
		stockname = StockName;
		price = Price;
		partial = Partial;
		type = Type;
	}

	public Data getData() {
		return this;
	}

	public void setData(int Time, String Name, int Texp, String Type, int Qty,
			char StockName, int Price, boolean Partial) {

		time = Time;
		name = Name;
		texp = Texp;
		qty = Qty;
		stockname = StockName;
		price = Price;
		partial = Partial;
		type = Type;
	}

	public Data getNext() {
		return next;
	}

	public void setNext(Data nextValue) {
		next = nextValue;
	}

}
