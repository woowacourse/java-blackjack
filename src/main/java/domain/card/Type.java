package domain.card;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public enum Type {
	SPADE("스페이드"),
	HEART("하트"),
	DIAMOND("다이아몬드"),
	CLOVER("클로버");

	private final String name;

	Type(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
