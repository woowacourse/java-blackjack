package blackjack.view;

import blackjack.view.exceptions.YesOrNoException;

public class YesOrNo {
	public static final String YES = "Y";
	public static final String NO = "N";

	private final String yesOrNo;

	private YesOrNo(String yesOrNo) {
		validateIsYesOrNo(yesOrNo);
		this.yesOrNo = yesOrNo;
	}

	public static YesOrNo of(String yesOrNo) {
		String upperCase = yesOrNo.trim().toUpperCase();
		return new YesOrNo(upperCase);
	}

	private void validateIsYesOrNo(String yesOrNo) {
		if (yesOrNo.equals(YES) || yesOrNo.equals(NO)) {
			return;
		}
		throw new YesOrNoException("Y나 N을 입력해야합니다.");
	}

	public boolean isYes() {
		return yesOrNo.equals(YES);
	}
}
