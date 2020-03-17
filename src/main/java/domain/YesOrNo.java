package domain;

public class YesOrNo {
	private static final String WRONG_INPUT_MESSAGE = "올바른 선택 값이 아닙니다.";

	private static final String YES_VALUE = "y";
	private static final String NO_VALUE = "n";

	private final String yesOrNo;

	public YesOrNo(String yesOrNo) {
		validateYesOrNo(yesOrNo);
		this.yesOrNo = yesOrNo;
	}

	private void validateYesOrNo(String yesOrNo) {
		if (isNotYesOrNo(yesOrNo)) {
			throw new IllegalArgumentException(WRONG_INPUT_MESSAGE);
		}
	}

	private boolean isNotYesOrNo(String yesOrNo) {
		return !(YES_VALUE.equals(yesOrNo) || NO_VALUE.equals(yesOrNo));
	}

	public boolean isContinue() {
		return YES_VALUE.equals(yesOrNo);
	}
}
