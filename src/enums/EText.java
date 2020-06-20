package enums;

import utils.Text;

public enum EText {

	BLANK("", TextTypeEnum.INDICATOR),
	YOU_WON("You won", TextTypeEnum.INDICATOR),
	YOU_LOST("You lost", TextTypeEnum.INDICATOR),
	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	ENEMY_PHASE("Enemy phase", TextTypeEnum.INDICATOR),
	DISCARD_PHASE("Discard phase", TextTypeEnum.INDICATOR),
	CHOOSE_CARDS_TO_DISCARD("Choose cards to discard", TextTypeEnum.INDICATOR),
	DRAW_PHASE("Draw phase", TextTypeEnum.INDICATOR),
	CAPTURE_PHASE("Capture phase", TextTypeEnum.INDICATOR),
	CAPTURE_ENEMY_CARD_OPTION("Capture enemy card", TextTypeEnum.OPTION),
	CAPTURE_ENEMY_CARD_INDICATOR("Capture enemy card", TextTypeEnum.INDICATOR),
	LET_YOUR_CARD_GET_CAPTURED("Let your card get captured", TextTypeEnum.OPTION),
	SACRIFICE_TWO_CARDS("Sacrifice two cards", TextTypeEnum.OPTION),
	
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
