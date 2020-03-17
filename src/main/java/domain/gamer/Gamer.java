package domain.gamer;

import java.util.Objects;

import domain.card.Card;
import domain.card.Hands;

/**
 *    게임 참가자 부모 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public abstract class Gamer {
	private String name;
	private Hands hands;

	public Gamer(String name) {
		validateNullAndEmpty(name);
		this.name = name;
		this.hands = new Hands();
	}

	private void validateNullAndEmpty(String name) {
		if ((name == null) || name.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	public void hit(Card card) {
		hands.add(card);
	}

	public boolean isBust() {
		return hands.isBust();
	}

	public int scoreHands() {
		return hands.calculateTotalScore();
	}

	public boolean isBlackjack() {
		return hands.isBlackjack();
	}

	public abstract boolean canHit();

	@Override
	public String toString() {
		return "Gamer{" +
			"name='" + name + '\'' +
			", hands=" + hands +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Gamer))
			return false;
		Gamer gamer = (Gamer)o;
		return Objects.equals(name, gamer.name) &&
			Objects.equals(hands, gamer.hands);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hands);
	}

	public Hands getHands() {
		return hands;
	}

	public String getName() {
		return name;
	}
}
