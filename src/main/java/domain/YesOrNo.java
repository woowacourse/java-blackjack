package domain;

public class YesOrNo {
	private final String yesOrNo;

	public YesOrNo(String yesOrNo) {
		this.yesOrNo = yesOrNo;
	}

	public boolean isContinue() {
		return this.yesOrNo.equals("y");
	}
}
