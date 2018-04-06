public class MobilePhone {
	int num;
	boolean state = false;
	Exchange e;

	MobilePhone(int n) {
		num = n;
	}

	public int number() {
		return num;
	}

	public boolean status() {
		return state;
	}

	public void switchOn(Exchange b) {
		state = true;
		e = b;
	}

	public void switchOff() {
		state = false;
	}

	public Exchange location() {
		if (state) {
			return e;
		} else {
			try {
				throw new MobilePhoneOffException("switched off");
			} catch (MobilePhoneOffException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
	}
}

class MobilePhoneOffException extends Exception {
	MobilePhoneOffException(String s) {
		super(s);
	}
}
