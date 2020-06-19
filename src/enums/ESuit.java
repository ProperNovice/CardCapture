package enums;

public enum ESuit {

	CLUBS("clubs"), DIAMONDS("diamonds"), HEARTS("hearts"), SPADES("spades"), BLACK("black"), RED("red");

	private String fileName = null;

	private ESuit(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

}
