public class Node {

	Node node;
	int time = -1;
	int texp = -1;
	int qty = -1;
	int price = -1;
	String name;
	String type;
	char stockname;
	boolean partial;

	public Node(String s) {
		String[] indi = s.split(" ");
		if (indi.length != 8) {
		} else {

			try {
				time = Integer.parseInt(indi[0]);
				texp = Integer.parseInt(indi[2]);
				qty = Integer.parseInt(indi[4]);
				price = Integer.parseInt(indi[6]);
			} catch (NumberFormatException e) {
			}
			String name = indi[1];
			String type = indi[3];
			char stockname = indi[5].charAt(0);
			boolean partial;
			if (indi[7].charAt(0) == 'T')
				partial = true;
			else if (indi[7].charAt(0) == 'F')
				partial = false;
			else
				partial = false;
		}

	}
}
