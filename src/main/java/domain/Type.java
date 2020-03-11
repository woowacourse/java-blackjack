package domain;

/**
 *    카드 타입
 *
 *    @author AnHyungJu, ParkDooWon
 */
public enum Type {
	HEART("♥"),
	SPADE("♠"),
	CLUBS("♣"),
	DIAMOND("◆");

	private String type;

	Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
