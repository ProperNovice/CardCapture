package enums;

public enum EValue {

	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13),
	ACE(1), JOKER(14);

	private String fileName = null;

	private EValue(int fileName) {
		this.fileName = Integer.toString(fileName);
	}

	public String getFileName() {
		return this.fileName;
	}

}
