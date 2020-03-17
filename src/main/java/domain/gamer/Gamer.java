package domain.gamer;

import java.util.List;

import domain.card.Card;
import domain.card.Hand;
import domain.result.Score;

public abstract class Gamer {
	private final Name name;
	private final Hand hand;

	public Gamer(Name name) {
		this.name = name;
		this.hand = Hand.fromEmpty();
	}

	protected abstract Score getHitPoint();

	public void hit(Card card) {
		hand.add(card);
	}

	public boolean canHit() {
		return Score.from(hand).isLowerThan(getHitPoint());
	}

	public Score getScore() {
		return Score.from(hand);
	}

	public boolean isBust() {
		return getScore().isBiggerThan(Score.BLACKJACK);
	}

	public boolean isBiggerThan(Gamer gamer) {
		return getScore().isBiggerThan(gamer.getScore());
	}

	public boolean isEqualTo(Gamer gamer) {
		return getScore().isEqualTo(gamer.getScore());
	}

	public boolean isLowerThan(Gamer gamer) {
		return getScore().isLowerThan(gamer.getScore());
	}

	public List<Card> getCards() {
		return hand.getCards();
	}

	public String getName() {
		return name.getName();
	}
}
