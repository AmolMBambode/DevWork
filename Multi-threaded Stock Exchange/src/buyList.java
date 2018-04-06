public class buyList {
	private int buyCounter;
	public buyElement buyHead;

	public buyList() {
		buyHead = null;
	}

	public synchronized void buyAdd(int Time, String Name, int Texp,
			String Type, int QtyTotal, int QtyAvailable, char StockName,
			int Price, boolean Partial) {
		if (buyHead == null) {
			buyHead = new buyElement(Time, Name, Texp, Type, QtyTotal,
					QtyAvailable, StockName, Price, Partial);
		}

		buyElement temp = new buyElement(Time, Name, Texp, Type, QtyTotal,
				QtyAvailable, StockName, Price, Partial);
		buyElement current = buyHead;

		if (current != null) {
			// start from head, go to last
			while (current.getNextElement() != null) {
				current = current.getNextElement();
			}

			current.setNextElement(temp);
		}
		incrementBuyCounter();

	}

	public synchronized int getBuyCounter() {
		return buyCounter;
	}

	private synchronized void incrementBuyCounter() {
		buyCounter++;
	}

	private synchronized void decrementBuyCounter() {
		buyCounter--;
	}

	public synchronized buyElement getBuy(int index) {
		if (index <= 0)
			return null;
		buyElement current = null;
		if (buyHead != null) {
			current = buyHead.getNextElement();
			for (int i = 0; i < index; i++) {
				if (current.getNextElement() == null)
					return null;

				current = current.getNextElement();
			}
			return current.getBuyData();
		} else
			return null;
	}

	public synchronized buyElement deleteBuy() {
		buyElement current = buyHead;
		buyElement temp = null;
		if (current != null) {
			// start from head, go to last
			if (current.nextElement == null) {
				if (current.qtyAvailable == 0
						|| ((double) (System.currentTimeMillis() - stock.systime) / 1000 > (current.time + current.texp))) {
					buyHead = null;
					decrementBuyCounter();
					return current;
				}
			} else if (current.nextElement.getNextElement() == null) {
				if (current.getNextElement().qtyAvailable == 0
						|| ((double) (System.currentTimeMillis() - stock.systime) / 1000 > (current
								.getNextElement().time + current
								.getNextElement().texp))) {
					temp = current.getNextElement();
					current.nextElement = (current.getNextElement()).nextElement;
					decrementBuyCounter();
					return temp;
				}
			} else {
				while (current.nextElement.getNextElement() != null) {
					if (current.getNextElement().qtyAvailable == 0
							|| ((double) (System.currentTimeMillis() - stock.systime) / 1000 > (current
									.getNextElement().time + current
									.getNextElement().texp))) {
						temp = current.getNextElement();
						current.nextElement = (current.getNextElement()).nextElement;
						decrementBuyCounter();
						current = current.getNextElement();

						return temp;
					}
				}
			}
		}
		return null;

	}
}
