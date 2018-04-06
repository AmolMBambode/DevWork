import java.util.*;
import java.io.*;

public class Exchange extends Thread {

	FileOutputStream fsExp = null;
	PrintStream pExp = null;

	boolean endi = true;
	
	public int tp = 0;
	
	long startTime = stock.systime;

	static sellList sl = new sellList();
	static buyList bl = new buyList();

	// constructor
	Exchange() {
		start();
		test t = new test();
	}

	// Run
	public void run() {
		try {
			fsExp = new FileOutputStream("exchange1.txt", false);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pExp = new PrintStream(fsExp);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// WATCH SLEEP COMMANDS
		while (true) {

			try {
				Data temp = stock.q.dequeue();

				// System.out.print(temp.name + " exchange " +temp.type);
				if (temp != null) {
					boolean value = checkType(temp);
					if (value)

					{
						pExp.println("P "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " 0 " + temp.time + " " + temp.name
								+ " " + temp.texp + " " + temp.type + " "
								+ temp.qty + " " + temp.stockname + " "
								+ temp.price + " " + temp.partial);
						buyAdd(temp);
					} else {
						pExp.println("S "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " 0 " + temp.time + " " + temp.name
								+ " " + temp.texp + " " + temp.type + " "
								+ temp.qty + " " + temp.stockname + " "
								+ temp.price + " " + temp.partial);
						sellAdd(temp);
					}

					profitMaximiser();
					temp = null;
				}
			} catch (NullPointerException e) {
				// System.out.print("  in catch  ");
				try {
					Thread.sleep(20);
				} catch (InterruptedException et) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			
			if(  (double)( System.currentTimeMillis() - stock.systime )/1000 >= (stock.maxTexp-0.20) && endi == true )
			{pExp.println(tp); endi = false;}
		}

	}

	public boolean checkType(Data temp) {
		if (temp.type.equalsIgnoreCase("buy"))
			return true;
		else
			return false;

	}

	public void profitMaximiser() {
	
		sellElement scurrent = sl.sellHead;
		buyElement bcurrent = bl.buyHead;
		int maxProfit = 0;
		String sName = "";
		String bName = "";
		int code = -1;
		if (scurrent != null) {
			while (scurrent.getNextElement() != null) {
				int r = scurrent.qtyAvailable;
				boolean status = scurrent.partial;
				if ((double) (System.currentTimeMillis() - stock.systime) / 1000 < (scurrent.time + scurrent.texp)) {
					if (bcurrent != null) {
						while (bcurrent.getNextElement() != null) {
							if ((double) (System.currentTimeMillis() - stock.systime) / 1000 < (bcurrent.time + bcurrent.texp)) {
								if (scurrent.stockname == bcurrent.stockname) {
									if (!status) {
										if (!bcurrent.partial) {
											if (r == bcurrent.qtyAvailable) {
												if (maxProfit < bcurrent.price
														* bcurrent.qtyAvailable
														- scurrent.price * r) {
													maxProfit = bcurrent.price
															* bcurrent.qtyAvailable
															- scurrent.price
															* r;
													sName = scurrent.name;
													bName = bcurrent.name;
													code = 1;
												}
											}
										} else {
											if (r <= bcurrent.qtyAvailable) {

												if (maxProfit < bcurrent.price
														* r - scurrent.price
														* r) {
													maxProfit = bcurrent.price
															* r
															- scurrent.price
															* r;
													sName = scurrent.name;
													bName = bcurrent.name;
													code = 2;
												}
											}
										}
									} else {
										if (!bcurrent.partial) {
											if (r >= bcurrent.qtyAvailable) {
												if (maxProfit < bcurrent.price
														* bcurrent.qtyAvailable
														- scurrent.price
														* bcurrent.qtyAvailable) {
													maxProfit = bcurrent.price
															* bcurrent.qtyAvailable
															- scurrent.price
															* bcurrent.qtyAvailable;
													sName = scurrent.name;
													bName = bcurrent.name;
													code = 3;
												}
											}
										} else {
											if (r <= bcurrent.qtyAvailable) {

												if (maxProfit < bcurrent.price
														* r - scurrent.price
														* r) {
													maxProfit = bcurrent.price
															* r
															- scurrent.price
															* r;
													sName = scurrent.name;
													bName = bcurrent.name;
													code = 4;
												}
											} else {
												if (maxProfit < bcurrent.price
														* bcurrent.qtyAvailable
														- scurrent.price
														* bcurrent.qtyAvailable) {
													maxProfit = bcurrent.price
															* bcurrent.qtyAvailable
															- scurrent.price
															* bcurrent.qtyAvailable;
													sName = scurrent.name;
													bName = bcurrent.name;
													code = 5;
												}
											}
										}
									}
								}
							}
							bcurrent = bcurrent.getNextElement();
						}
					}
					bcurrent = bl.buyHead;
					if (bcurrent != null) {
						while (bcurrent.getNextElement() != null) {
							if (bcurrent.name.equals(bName)) {
								switch (code) {
								case 1:
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / bcurrent.qtyAvailable)
											+ " " + scurrent.time + " "
											+ scurrent.name + " "
											+ scurrent.texp + " "
											+ scurrent.type + " "
											+ scurrent.qtyTotal + " "
											+ scurrent.stockname + " "
											+ scurrent.price + " "
											+ scurrent.partial);
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / bcurrent.qtyAvailable)
											+ " " + bcurrent.time + " "
											+ bcurrent.name + " "
											+ bcurrent.texp + " "
											+ bcurrent.type + " "
											+ bcurrent.qtyTotal + " "
											+ bcurrent.stockname + " "
											+ bcurrent.price + " "
											+ bcurrent.partial);
									tp += maxProfit;
									bcurrent.qtyAvailable = 0;
									scurrent.qtyAvailable = 0;
									break;
								case 2:
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / bcurrent.qtyAvailable)
											+ " " + scurrent.time + " "
											+ scurrent.name + " "
											+ scurrent.texp + " "
											+ scurrent.type + " "
											+ scurrent.qtyTotal + " "
											+ scurrent.stockname + " "
											+ scurrent.price + " "
											+ scurrent.partial);
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / bcurrent.qtyAvailable)
											+ " " + bcurrent.time + " "
											+ bcurrent.name + " "
											+ bcurrent.texp + " "
											+ bcurrent.type + " "
											+ bcurrent.qtyTotal + " "
											+ bcurrent.stockname + " "
											+ bcurrent.price + " "
											+ bcurrent.partial);
									tp += maxProfit;
									scurrent.qtyAvailable = scurrent.qtyAvailable
											- bcurrent.qtyAvailable;
									bcurrent.qtyAvailable = 0;
									break;
								case 3:
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / scurrent.qtyAvailable)
											+ " " + scurrent.time + " "
											+ scurrent.name + " "
											+ scurrent.texp + " "
											+ scurrent.type + " "
											+ scurrent.qtyTotal + " "
											+ scurrent.stockname + " "
											+ scurrent.price + " "
											+ scurrent.partial);
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / scurrent.qtyAvailable)
											+ " " + bcurrent.time + " "
											+ bcurrent.name + " "
											+ bcurrent.texp + " "
											+ bcurrent.type + " "
											+ bcurrent.qtyTotal + " "
											+ bcurrent.stockname + " "
											+ bcurrent.price + " "
											+ bcurrent.partial);
									tp += maxProfit;
									bcurrent.qtyAvailable = bcurrent.qtyAvailable
											- scurrent.qtyAvailable;
									scurrent.qtyAvailable = 0;
									break;
								case 4:
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / bcurrent.qtyAvailable)
											+ " " + scurrent.time + " "
											+ scurrent.name + " "
											+ scurrent.texp + " "
											+ scurrent.type + " "
											+ scurrent.qtyTotal + " "
											+ scurrent.stockname + " "
											+ scurrent.price + " "
											+ scurrent.partial);
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / bcurrent.qtyAvailable)
											+ " " + bcurrent.time + " "
											+ bcurrent.name + " "
											+ bcurrent.texp + " "
											+ bcurrent.type + " "
											+ bcurrent.qtyTotal + " "
											+ bcurrent.stockname + " "
											+ bcurrent.price + " "
											+ bcurrent.partial);
									tp += maxProfit;
									scurrent.qtyAvailable = scurrent.qtyAvailable
											- bcurrent.qtyAvailable;
									bcurrent.qtyAvailable = 0;
									break;
								case 5:
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / scurrent.qtyAvailable)
											+ " " + scurrent.time + " "
											+ scurrent.name + " "
											+ scurrent.texp + " "
											+ scurrent.type + " "
											+ scurrent.qtyTotal + " "
											+ scurrent.stockname + " "
											+ scurrent.price + " "
											+ scurrent.partial);
									pExp.println("T "
											+ (double) (System
													.currentTimeMillis() - stock.systime)
											/ 1000
											+ " "
											+ (int) (maxProfit / scurrent.qtyAvailable)
											+ " " + bcurrent.time + " "
											+ bcurrent.name + " "
											+ bcurrent.texp + " "
											+ bcurrent.type + " "
											+ bcurrent.qtyTotal + " "
											+ bcurrent.stockname + " "
											+ bcurrent.price + " "
											+ bcurrent.partial);
									tp += maxProfit;
									bcurrent.qtyAvailable = bcurrent.qtyAvailable
											- scurrent.qtyAvailable;
									scurrent.qtyAvailable = 0;
									break;
								default:
									break;
								}
							}
							bcurrent = bcurrent.getNextElement();
						}
					}

				}
				scurrent = scurrent.getNextElement();
			}
		}
	}

	public void buyAdd(Data temp) {
		sellElement current = sl.sellHead;
		

		int maxProfit = 0;
		int code = 0;
		String pName = "";
		if (current != null) {

			while (current.getNextElement() != null) {
				if (current.stockname == temp.stockname
						&& (double) (System.currentTimeMillis() - stock.systime) / 1000 < (current.time + current.texp)
						&& (double) (System.currentTimeMillis() - stock.systime) / 1000 < (temp.time + temp.texp)) {
					

					if (!current.partial) {
					

						if (!temp.partial) {
						

							if (current.qtyAvailable == temp.qty) {

								if (maxProfit < (-current.price
										* current.qtyAvailable + temp.price
										* temp.qty)) {
									maxProfit = (-current.price
											* current.qtyAvailable + temp.price
											* temp.qty);
									pName = current.name;
									code = 1;
								}
							}
						} else {

							if (current.qtyAvailable <= temp.qty) {

								if (maxProfit < (-current.price
										* current.qtyAvailable + temp.price
										* current.qtyAvailable)) {
									maxProfit = (-current.price
											* current.qtyAvailable + temp.price
											* current.qtyAvailable);
									pName = current.name;
									code = 2;
								}
							}
						}
					} else {

						if (!temp.partial) {

							if (current.qtyAvailable >= temp.qty) {
								if (maxProfit < (-current.price * temp.qty + temp.price
										* temp.qty)) {
									maxProfit = (-current.price * temp.qty + temp.price
											* temp.qty);
									pName = current.name;
									code = 3;
								}
							}
						} else {

							if (current.qtyAvailable <= temp.qty) {

								if (maxProfit < (-current.price
										* current.qtyAvailable + temp.price
										* current.qtyAvailable)) {
									maxProfit = (-current.price
											* current.qtyAvailable + temp.price
											* current.qtyAvailable);
									pName = current.name;
									code = 4;
								}
							}
							if (current.qtyAvailable >= temp.qty) {

								if (maxProfit < (-current.price * temp.qty + temp.price
										* temp.qty)) {
									maxProfit = (-current.price * temp.qty + temp.price
											* temp.qty);
									pName = current.name;
									code = 5;
								}
							}
						}
					}
				}
				current = current.getNextElement();
			}

		}
		if (maxProfit > 0) {
			current = sl.sellHead;
			if (current != null) {
				while (current.getNextElement() != null) {
					if (current.name.equals(pName)) {
						switch (code) {
						case 1:
							pExp.println("T "
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + current.time + " "
									+ current.name + " " + current.texp + " "
									+ current.type + " " + current.qtyTotal
									+ " " + current.stockname + " "
									+ current.price + " " + current.partial);
							pExp.println("T buy"
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + temp.time + " "
									+ temp.name + " " + temp.texp + " "
									+ temp.type + " " + temp.qty + " "
									+ temp.stockname + " " + temp.price + " "
									+ temp.partial);
							tp += maxProfit;
							current.qtyAvailable = 0;
							temp.qty = 0;

							break;
						case 2:
							pExp.println("T "
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + current.time + " "
									+ current.name + " " + current.texp + " "
									+ current.type + " " + current.qtyTotal
									+ " " + current.stockname + " "
									+ current.price + " " + current.partial);
							pExp.println("T buy"
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + temp.time + " "
									+ temp.name + " " + temp.texp + " "
									+ temp.type + " " + temp.qty + " "
									+ temp.stockname + " " + temp.price + " "
									+ temp.partial);
							tp += maxProfit;
							current.qtyAvailable = 0;
							temp.qty = temp.qty - current.qtyAvailable;
							break;
						case 3:
							pExp.println("T "
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + current.time + " "
									+ current.name + " " + current.texp + " "
									+ current.type + " " + current.qtyTotal
									+ " " + current.stockname + " "
									+ current.price + " " + current.partial);
							pExp.println("T buy"
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + temp.time + " "
									+ temp.name + " " + temp.texp + " "
									+ temp.type + " " + temp.qty + " "
									+ temp.stockname + " " + temp.price + " "
									+ temp.partial);
							tp += maxProfit;
							current.qtyAvailable = current.qtyAvailable
									- temp.qty;
							temp.qty = 0;
							break;
						case 4:
							pExp.println("T "
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + current.time + " "
									+ current.name + " " + current.texp + " "
									+ current.type + " " + current.qtyTotal
									+ " " + current.stockname + " "
									+ current.price + " " + current.partial);
							pExp.println("T buy"
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + temp.time + " "
									+ temp.name + " " + temp.texp + " "
									+ temp.type + " " + temp.qty + " "
									+ temp.stockname + " " + temp.price + " "
									+ temp.partial);
							tp += maxProfit;
							current.qtyAvailable = 0;
							temp.qty = temp.qty - current.qtyAvailable;
							break;
						case 5:
							pExp.println("T "
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + current.time + " "
									+ current.name + " " + current.texp + " "
									+ current.type + " " + current.qtyTotal
									+ " " + current.stockname + " "
									+ current.price + " " + current.partial);
							pExp.println("T buy"
									+ (double) (System.currentTimeMillis() - stock.systime)
									/ 1000 + " " + " " + temp.time + " "
									+ temp.name + " " + temp.texp + " "
									+ temp.type + " " + temp.qty + " "
									+ temp.stockname + " " + temp.price + " "
									+ temp.partial);
							tp += maxProfit;
							current.qtyAvailable = current.qtyAvailable
									- temp.qty;
							temp.qty = 0;
							break;
						default:
							break;
						}
					}
					current = current.getNextElement();
				}
			}
		}
		if (temp.qty != 0
				&& (double) (System.currentTimeMillis() - stock.systime) / 1000 < (temp.time + temp.texp)) {

			bl.buyAdd(temp.time, temp.name, temp.texp, temp.type, temp.qty,
					temp.qty, temp.stockname, temp.price, temp.partial);
		}

	}

	public void sellAdd(Data temp) {
		buyElement current = bl.buyHead;
		int maxProfit = 0;
		String pName = "";
		int code = -1;
		if (current != null) {
			while (current.getNextElement() != null) {

				if (current.stockname == temp.stockname
						&& (double) (System.currentTimeMillis() - stock.systime) / 1000 < (current.time + current.texp)
						&& (double) (System.currentTimeMillis() - stock.systime) / 1000 < (temp.time + temp.texp)) {

					if (!current.partial) {

						if (!temp.partial) {

							if (current.qtyAvailable == temp.qty) {
								if (maxProfit < (+current.price
										* current.qtyAvailable - temp.price
										* temp.qty)) {
									maxProfit = (current.price
											* current.qtyAvailable - temp.price
											* temp.qty);
									pName = current.name;
									code = 1;

								}
							}
						} else {

							if (current.qtyAvailable <= temp.qty) {
								if (maxProfit < (+current.price
										* current.qtyAvailable - temp.price
										* current.qtyAvailable)) {
									maxProfit = (+current.price
											* current.qtyAvailable - temp.price
											* current.qtyAvailable);
									pName = current.name;
									code = 2;

								}
							}
						}
					} else {

						if (!temp.partial) {

							if (current.qtyAvailable >= temp.qty) {
								if (maxProfit < (current.price * temp.qty - temp.price
										* temp.qty)) {
									maxProfit = (current.price * temp.qty - temp.price
											* temp.qty);
									pName = current.name;
									code = 3;

								}
							}
						} else {

							if (current.qtyAvailable <= temp.qty) {

								if (maxProfit < (current.price
										* current.qtyAvailable - temp.price
										* current.qtyAvailable)) {
									maxProfit = (current.price
											* current.qtyAvailable - temp.price
											* current.qtyAvailable);
									pName = current.name;
									code = 4;

								}
							}
							if (current.qtyAvailable >= temp.qty) {
								System.out
										.println(" qty satisfied 2"
												+ (current.price * temp.qty - temp.price
														* temp.qty));

								if (maxProfit < (current.price * temp.qty - temp.price
										* temp.qty)) {
									maxProfit = (current.price * temp.qty - temp.price
											* temp.qty);
									pName = current.name;
									code = 5;

								}
							}
						}
					}
				}
				current = current.getNextElement();
			}
		}

		current = bl.buyHead;
		if (current != null) {
			while (current.getNextElement() != null) {
				if (current.name.equals(pName)) {
					switch (code) {
					case 1:
						pExp.println("T "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + " " + current.time + " "
								+ current.name + " " + current.texp + " "
								+ current.type + " " + current.qtyTotal + " "
								+ current.stockname + " " + current.price + " "
								+ current.partial);
						pExp.println("T "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + " " + temp.time + " "
								+ temp.name + " " + temp.texp + " " + temp.type
								+ " " + temp.qty + " " + temp.stockname + " "
								+ temp.price + " " + temp.partial);
						tp += maxProfit;
						current.qtyAvailable = 0;
						temp.qty = 0;
						break;
					case 2:
						pExp.println("T "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + " " + current.time + " "
								+ current.name + " " + current.texp + " "
								+ current.type + " " + current.qtyTotal + " "
								+ current.stockname + " " + current.price + " "
								+ current.partial);
						pExp.println("T "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + " " + temp.time + " "
								+ temp.name + " " + temp.texp + " " + temp.type
								+ " " + temp.qty + " " + temp.stockname + " "
								+ temp.price + " " + temp.partial);
						tp += maxProfit;
						temp.qty = temp.qty - current.qtyAvailable;
						current.qtyAvailable = 0;
						break;
					case 3:
						pExp.println("T "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + " " + current.time + " "
								+ current.name + " " + current.texp + " "
								+ current.type + " " + current.qtyTotal + " "
								+ current.stockname + " " + current.price + " "
								+ current.partial);
						pExp.println("T "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + " " + temp.time + " "
								+ temp.name + " " + temp.texp + " " + temp.type
								+ " " + temp.qty + " " + temp.stockname + " "
								+ temp.price + " " + temp.partial);
						tp += maxProfit;
						current.qtyAvailable = current.qtyAvailable - temp.qty;
						temp.qty = 0;
						break;
					case 4:
						pExp.println("T "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + " " + current.time + " "
								+ current.name + " " + current.texp + " "
								+ current.type + " " + current.qtyTotal + " "
								+ current.stockname + " " + current.price + " "
								+ current.partial);
						pExp.println("T sell "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + " " + temp.time + " "
								+ temp.name + " " + temp.texp + " " + temp.type
								+ " " + temp.qty + " " + temp.stockname + " "
								+ temp.price + " " + temp.partial);
						tp += maxProfit;
						temp.qty = temp.qty - current.qtyAvailable;
						current.qtyAvailable = 0;
						break;
					case 5:
						pExp.println("T "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + current.time + " "
								+ current.name + " " + current.texp + " "
								+ current.type + " " + current.qtyTotal + " "
								+ current.stockname + " " + current.price + " "
								+ current.partial);
						pExp.println("T sell "
								+ (double) (System.currentTimeMillis() - stock.systime)
								/ 1000 + " " + temp.time + " " + temp.name
								+ " " + temp.texp + " " + temp.type + " "
								+ temp.qty + " " + temp.stockname + " "
								+ temp.price + " " + temp.partial);
						tp += maxProfit;
						current.qtyAvailable = current.qtyAvailable - temp.qty;
						temp.qty = 0;
						break;
					default:
						break;
					}
				}
				current = current.getNextElement();
			}
		}
		if (temp.qty != 0
				&& (double) (System.currentTimeMillis() - stock.systime) / 1000 < (temp.time + temp.texp)) {
			sl.sellAdd(temp.time, temp.name, temp.texp, temp.type, temp.qty,
					temp.qty, temp.stockname, temp.price, temp.partial);
		}

	}

}
