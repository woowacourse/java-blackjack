package domain;

public class YesOrNo {
	private static final String YES_VALUE = "y";
	private static final String NO_VALUE = "n";

	private final String yesOrNo;

	public YesOrNo(String yesOrNo) {
		validateYesOrNo(yesOrNo);
		this.yesOrNo = yesOrNo;
	}

	private void validateYesOrNo(String yesOrNo) {
		if (!(YES_VALUE.equals(yesOrNo) || NO_VALUE.equals(yesOrNo))) {
			throw new IllegalArgumentException("올바른 선택 값이 아닙니다.");
		}
	}

	public boolean isContinue() {
		return this.yesOrNo.equals(YES_VALUE);
	}
}
