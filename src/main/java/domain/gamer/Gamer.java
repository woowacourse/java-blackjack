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
		this.name = name;
		this.hands = new Hands();
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
}
