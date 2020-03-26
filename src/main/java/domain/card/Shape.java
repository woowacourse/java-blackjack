package domain.card;

public enum Shape {
	DIAMOND("다이아몬드"),
	SPADE("스페이드"),
	HEART("하트"),
	CLOVER("클로버");

	private String koreanName;

	Shape(String koreanName) {
		this.koreanName = koreanName;
	}

	public String getKoreanName() {
		return koreanName;
	}
}
