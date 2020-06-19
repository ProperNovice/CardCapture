package enums;

import utils.Text;

public enum EText {

	BLANK("", TextTypeEnum.INDICATOR),
	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	ENEMY_PHASE("Enemy phase", TextTypeEnum.INDICATOR),
	DISCARD_PHASE("Discard phase", TextTypeEnum.INDICATOR),
	CHOOSE_CARDS_TO_DISCARD("Choose cards to discard", TextTypeEnum.INDICATOR),

	;

	private String string = null;
	private TextTypeEnum textTypeEnum = null;

	public enum TextTypeEnum {
		INDICATOR, OPTION
	}

	private EText(String string, TextTypeEnum textTypeEnum) {
		this.string = string;
		this.textTypeEnum = textTypeEnum;
	}
	
	public void showText() {
		Text.INSTANCE.showText(this);
	}

	public String getString() {
		return this.string;
	}

	public TextTypeEnum getTextTypeEnum() {
		return this.textTypeEnum;
	}

}
