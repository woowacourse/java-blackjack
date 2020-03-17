package blackjack.domain.user;

import blackjack.domain.user.exceptions.YesOrNoException;

public final class YesOrNo {
	public static final String YES = "Y";
	public static final String NO = "N";

	private final String yesOrNo;

	private YesOrNo(String yesOrNo) {
		validateIsYesOrNo(yesOrNo);
		this.yesOrNo = yesOrNo;
	}

	private void validateIsYesOrNo(String yesOrNo) {
		if (YES.equals(yesOrNo) || NO.equals(yesOrNo)) {
			return;
		}
		throw new YesOrNoException("Y/y나 N/n을 입력해야합니다.");
	}

	public static YesOrNo of(String yesOrNo) {
		if (yesOrNo == null) {
			return new YesOrNo("");
		}

		String upperCase = yesOrNo.trim().toUpperCase();
		return new YesOrNo(upperCase);
	}

	public boolean isYes() {
		return yesOrNo.equals(YES);
	}
}
