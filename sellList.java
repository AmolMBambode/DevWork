public class sellList {
	private int sellCounter;
	public sellElement sellHead;

	public sellList() {
		sellHead = null;
	}

	public synchronized void sellAdd(int Time, String Name, int Texp,
			String Type, int QtyTotal, int QtyAvailable, char StockName,
			int Price, boolean Partial) {
		if (sellHead == null) {
			sellHead = new sellElement(Time, Name, Texp, Type, QtyTotal,
					QtyAvailable, StockName, Price, Partial);
		}

		sellElement temp = new sellElement(Time, Name, Texp, Type, QtyTotal,
				QtyAvailable, StockName, Price, Partial);
		sellElement current = sellHead;

		if (current != null) {
			// start from head, go to last
			while (current.getNextElement() != null) {
				current = current.getNextElement();
			}

			current.setNextElement(temp);
		}
		incrementSellCounter();

	}

	public synchronized int getSellCounter() {
		return sellCounter;
	}

	private synchronized void incrementSellCounter() {
		sellCounter++;
	}

	private synchronized void decrementSellCounter() {
		sellCounter--;
	}

	public synchronized sellElement getSell(int index)
	// returns the element at the specified position in this list.
	{
		// index must be 1 or higher
		if (index <= 0)
			return null;
		sellElement current = null;
		if (sellHead != null) {
			current = sellHead.getNextElement();
			for (int i = 0; i < index; i++) {
				if (current.getNextElement() == null)
					return null;

				current = current.getNextElement();
			}
			return current.getSellData();
		} else
			return null;
	}

	public synchronized sellElement deleteSell() {
		sellElement current = sellHead;
		sellElement temp = null;
		if (current != null) {
			// start from head, go to last
			if (current.getNextElement() == null) {
				if (current.qtyAvailable == 0
						|| ((double) (System.currentTimeMillis() - stock.systime) / 1000 > (current.time + current.texp))) {
					sellHead = null;
					decrementSellCounter();
					return current;
				}
			} else if (current.nextElement.getNextElement() == null) {
				if (current.getNextElement().qtyAvailable == 0
						|| ((double) (System.currentTimeMillis() - stock.systime) / 1000 > (current
								.getNextElement().time + current
								.getNextElement().texp))) {
					temp = current.getNextElement();
					current.nextElement = (current.getNextElement()).nextElement;
					decrementSellCounter();
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
						decrementSellCounter();
						current = current.getNextElement();
						return temp;
					}
				}
			}
		}
		return null;

	}
}
