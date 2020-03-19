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
	private Name name;
	private Hands hands;

	public Gamer(Name name) {
		this.name = name;
		this.hands = new Hands();
	}

	public abstract boolean canHit();

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

	public boolean wins(int score) {
		return (score > Hands.BLACKJACK_SCORE) || ((this.scoreHands() <= Hands.BLACKJACK_SCORE) && (score
			< this.scoreHands()));
	}

	public boolean isPush(int score) {
		return score <= Hands.BLACKJACK_SCORE && (score == this.scoreHands());
	}

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
		return name.getName();
	}
}