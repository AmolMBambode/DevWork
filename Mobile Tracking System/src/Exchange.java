public class Exchange {
	int Identifier;
	Exchange baap;
	Exchange LeftAulad;
	Exchange bhai;

	ExchangeList el = new ExchangeList();
	MobilePhoneSet mps = new MobilePhoneSet();

	Exchange(int num) {
		Identifier = num;
		baap = null;
		LeftAulad = null;
		bhai = null;

	}

	public Exchange parent() {
		return baap;
	}

	public int numChildren() {
		return el.Elist.ll.size();
	}

	public Exchange child(int i) {
		if (i < el.Elist.ll.size())
			return el.getEL(i);
		else
			return null;
	}

	public boolean isRoot() {
		if (baap == null)
			return true;
		else
			return false;
	}

	// public RoutingMapTree subtree(int i){}

	public MobilePhoneSet residentSet() {
		return mps;
	}

}
