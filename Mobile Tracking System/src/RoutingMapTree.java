public class RoutingMapTree {

	public Exchange root;
	// search variables
	public Exchange varForSwitchon = null;
	public Exchange varForSwithchoff = null;

	public RoutingMapTree() {
		root = new Exchange(0);
	}

	public boolean containExchange(int e) {
		int f = 1;
		if (root.Identifier == e)
			return true;
		else {
			if (root.LeftAulad != null)
				f = f * recursionContain(f, root.LeftAulad, e);
		}
		if (f == 1)
			return false;
		else
			return true;
	}

	int recursionContain(int f, Exchange current, int e) {
		if(f==0)
			return f;
		if (current.Identifier == e)
			f = 0;
		if (current.LeftAulad != null)
			f = f * recursionContain(f, current.LeftAulad, e);
		if (current.bhai != null)
			f = f * recursionContain(f, current.bhai, e);
		return f;
	}

	public void addExchange(int baap, int bacha) {
		if (containExchange(baap)) {
			find(bacha, baap, root);
		} else
			// throw exception !!
			try {
				throw new ExchangeNotFoundException("donno why");
			} catch (ExchangeNotFoundException e) {
				System.out.println("Exchange not FOUND");
			}
	}

	void find(int bacha, int baap, Exchange Root) {
		Exchange exbacha = new Exchange(bacha);
		if (Root.Identifier == baap) {
			exbacha.baap = Root;
			if (Root.LeftAulad == null) {
				Root.el.InsertEL(exbacha);
				Root.LeftAulad = exbacha;
			} else {
				Root.el.Elist.Insert(exbacha);
				for (int i = 0; i < Root.el.Elist.ll.size(); i++) {
					if (Root.el.getEL(i) == exbacha)
						Root.el.getEL(i - 1).bhai = exbacha;
				}
			}
		}
		if (Root.LeftAulad != null)
			find(bacha, baap, Root.LeftAulad);
		if (Root.bhai != null)
			find(bacha, baap, Root.bhai);
	}

	public void switchOn(int MPa, int Exb) {
		boolean asd = false;
		boolean pqr = false;
		int p = -1;
		Exchange currentB = null, copyB = null, currentA = null;
		for (int i = 0; i < root.mps.phoneList.ll.size(); i++) {
			if (MPa == root.mps.getMPS(i).num) {
				asd = true;
				p = i;
			}
		}
		if (containExchange(Exb)) {
			findEx(Exb, root);
			if(varForSwitchon.LeftAulad != null){
				try {
					throw new notBaseStationException("aaa");
				} catch (notBaseStationException e) {
					System.out.println("Expecting a base station");
					return;
				}
			}
			currentB = copyB = varForSwitchon;

			if (asd) {
				currentA = root.mps.getMPS(p).e;
					while (currentA.baap != null) {
						
						if(pqr){
							currentA = currentA.baap;
							for (int i = 0; i < currentA.mps.phoneList.ll.size(); i++) {
								if (MPa == currentA.mps.getMPS(i).num) {
									p = i;
								}
							}
							System.out.println("earlier info of--- " + currentA . Identifier  + " " +(currentA.mps.getMPS(p)).num + " " + (currentA.mps.getMPS(p)).state  );
							(currentA.mps.getMPS(p)).state = true;
							System.out.println("info of--- " + currentA . Identifier  + " " +(currentA.mps.getMPS(p)).num + " " + (currentA.mps.getMPS(p)).state  );
							
						}
						else if(currentA == currentB && !pqr){
							currentA.mps.DeleteMPS(MPa);
							currentB.mps.InsertMPS(MPa, true, copyB);

							pqr = true;
						}
						else if (currentA.baap == currentB.baap && !pqr) {
							currentA.mps.DeleteMPS(MPa);
							currentB.mps.InsertMPS(MPa, true, copyB);

							pqr = true;
						} else {
							currentA.mps.DeleteMPS(MPa);
							currentB.mps.InsertMPS(MPa, true, copyB);
							currentA = currentA.baap;
							currentB = currentB.baap;
						}
					}
					
			} else {
				root.mps.InsertMPS(MPa, true, copyB);
				while (currentB.baap != null) {
					currentB.mps.InsertMPS(MPa, true, copyB);
					currentB = currentB.baap;
				}
			}
		} else
			try {
				throw new ExchangeNotFoundException("Exchange Not Found");
			} catch (ExchangeNotFoundException e) {}
	}

	void findEx(int b, Exchange Root) {
		if (Root.Identifier == b) {
			varForSwitchon = Root;
		}
		if (Root.LeftAulad != null)
			findEx(b, Root.LeftAulad);
		if (Root.bhai != null)
			findEx(b, Root.bhai);
	}

	public void switchOff(int a) {
		boolean asd = false;
		int position = -1;
		for (int i = 0; i < root.mps.phoneList.ll.size(); i++)
			if (a == root.mps.getMPS(i).num) {
				asd = true;
				position = i;
			}
		if (asd) {
			Exchange current = root.mps.getMPS(position).e;
			while (current.baap != null) {
				for (int i = 0; i < current.mps.phoneList.ll.size(); i++) {
					if (current.mps.getMPS(i).num == a)
						current.mps.getMPS(i).state = false;
				}
				current = current.baap;
			}
			for (int i = 0; i < root.mps.phoneList.ll.size(); i++) {
				if (root.mps.getMPS(i).num == a)
					root.mps.getMPS(i).state = false;
			}
		} else
			try {
				throw new MobilePhoneNotFoundException("non existing mobile phone");
			} catch (MobilePhoneNotFoundException e) {
				System.out.println("non existing mobile phone");
			}
	}

	int queryNthChild(int a, int b) {
		if (containExchange(a)) {
			findEx(a, root);
			Exchange AkaEx = varForSwitchon;
			if (AkaEx.el.Elist.ll.size() > b)
				return AkaEx.el.getEL(b).Identifier;
			else
				return -1;
		} else
			return -2;
	}

	MobilePhoneSet queryMobilePhoneSet(int a) {
		if (containExchange(a)) {
			findEx(a, root);
			Exchange AkaEx = varForSwitchon;
			return AkaEx.mps;
		} else
			return null;
	}

	public void performAction(String actionMessage) {
		System.out.println();
		System.out.println(actionMessage);
		int a, b;
		String[] arr = actionMessage.split(" ");
		try {
			if (arr[0].equals("addExchange") && arr.length == 3) {
				a = Integer.parseInt(arr[1]);
				b = Integer.parseInt(arr[2]);
				addExchange(a, b);
			} else if (arr[0].equals("switchOnMobile") && arr.length == 3) {
				a = Integer.parseInt(arr[1]);
				b = Integer.parseInt(arr[2]);
				switchOn(a, b);
			} else if (arr[0].equals("switchOffMobile") && arr.length == 2) {
				a = Integer.parseInt(arr[1]);
				switchOff(a);
			} else if (arr[0].equals("queryNthChild") && arr.length == 3) {
				a = Integer.parseInt(arr[1]);
				b = Integer.parseInt(arr[2]);
				int result = queryNthChild(a, b);
				if (result != -1 || result != -2)
					System.out.println(result);
				else if (result == -1)
					System.out.println("Exchange does not exist");
				else
					System.out
							.println("Mobile Phone set of Exchange a does not contains bth phone");
			} else if (arr[0].equals("queryMobilePhoneSet") && arr.length == 2) {
				a = Integer.parseInt(arr[1]);
				MobilePhoneSet mobiles = queryMobilePhoneSet(a);
				if (mobiles != null) {
					for (int i = 0; i < mobiles.phoneList.ll.size(); i++) {
						if (mobiles.getMPS(i).state)
							System.out.print(mobiles.getMPS(i).num + "  ");
					}
					System.out.println();
				} else
					try {
						throw new ExchangeNotFoundException("Exchange not found or List is Empty");
					} catch (ExchangeNotFoundException e) {
						System.out.println("Exchange not found or List is Empty");
					}
			} else
				try {
					throw new IllegalCommandException("illegal command");
				} catch (IllegalCommandException e) {
					System.out.println("illegal command");
				}
		} catch (NumberFormatException e) {
			System.out.println("inValid arguments passed");
		}
	}
}

class ExchangeNotFoundException extends Exception {
	ExchangeNotFoundException(String s) {
		super(s);
	}
}

class MobilePhoneNotFoundException extends Exception {
	MobilePhoneNotFoundException(String s) {
		super(s);
	}
}

class IllegalCommandException extends Exception {
	IllegalCommandException(String s) {
		super(s);
	}
}
class notBaseStationException extends Exception {
	notBaseStationException(String s) {
		super(s);
	}
}

