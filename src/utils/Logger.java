package utils;

public enum Logger {

	INSTANCE;

	private boolean logging = true;

	public void log(Object log) {

		if (!this.logging)
			return;

		System.out.println(log);

	}

	public void logClass(Object object) {

		if (!this.logging)
			return;

		log(object.getClass());

	}

	public void logNewLine(Object log) {

		if (!this.logging)
			return;

		log(log);
		newLine();

	}

	public void newLine() {

		if (!this.logging)
			return;

		System.out.println();
	}

}
