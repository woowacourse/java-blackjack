package domain.gamer;

import domain.card.Card;
import domain.card.Hands;

/**
 *    게임 참가자 부모 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Gamer {
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

	public void add(Card card) {
		hands.add(card);
	}

	public boolean isBust() {
		return hands.calculateTotalScore() > Hands.BLACKJACK;
	}

	public int scoreHands() {
		return hands.calculateTotalScore();
	}

	public boolean isBlackjack() {
		return (hands.calculateTotalScore() == Hands.BLACKJACK) && hands.hasTwoCards();
	}

	public Hands getHands() {
		return hands;
	}

	public String getName() {
		return name;
	}
}
