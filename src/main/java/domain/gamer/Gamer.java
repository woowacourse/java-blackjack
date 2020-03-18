package domain.gamer;

import java.util.List;

import domain.card.Card;
import domain.card.Hand;
import domain.result.Rule;
import domain.result.Score;

public abstract class Gamer {
	private final Hand hand;
	private final Name name;

	public Gamer(Name name) {
		this.name = name;
		this.hand = Hand.fromEmpty();
	}

	public boolean hasTwoCards() {
		return hand.hasTwoCards();
	}

	public void hit(Card card) {
		hand.add(card);
	}

	public int calculateCardSum() {
		return hand.calculate();
	}

	public boolean hasAce() {
		return hand.hasAce();
	}

	public boolean canHit() {
		return Rule.canHit(this);
	}

	public boolean isBust() {
		return Rule.isBust(this);
	}

	public boolean isNotBust() {
		return Rule.isNotBust(this);
	}

	public boolean isBlackJack() {
		return Rule.isBlackJack(this);
	}

	public boolean isNotBlackJack() {
		return Rule.isNotBlackJack(this);
	}

	public List<Card> getCards() {
		return hand.getCards();
	}

	public String getName() {
		return name.getName();
	}

	public Score getScore() {
		return Rule.calculateScore(this);
	}

	public abstract int getHitPoint();
}
