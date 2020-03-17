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
		return Score.calculate(hand).isLowerThan(getHitPoint());
	}

	public Score calculateScore() {
		return Score.calculate(hand);
	}

	public List<Card> getCards() {
		return hand.getCards();
	}

	public String getName() {
		return name.getName();
	}
}
