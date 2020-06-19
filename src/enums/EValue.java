package enums;

public enum EValue {

	TWO(2, 2), THREE(3, 3), FOUR(4, 4), FIVE(5, 5), SIX(6, 6), SEVEN(7, 7), EIGHT(8, 8), NINE(9, 9), TEN(10, 10),
	JACK(11, 11), QUEEN(12, 12), KING(13, 13), ACE(1, 14), JOKER(14, -1);

	private String fileName = null;
	private int pointValue = -1;

	private EValue(int fileName, int pointValue) {
		this.fileName = Integer.toString(fileName);
		this.pointValue = pointValue;
	}

	public String getFileName() {
		return this.fileName;
	}

	public int getPointValue() {
		return this.pointValue;
	}

}
