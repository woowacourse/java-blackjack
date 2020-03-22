package domain.gamer;

import domain.card.Card;
import domain.card.Hand;
import domain.result.Score;
import domain.rule.Rule;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public abstract class Gamer {
	private static final int ZERO = 0;

	private final Name name;
	private final Hand hand;

	public Gamer(Name name) {
		this.name = name;
		this.hand = Hand.createEmpty();
	}

	protected abstract Function<Hand, Boolean> hitStrategy();

	protected abstract int firstOpenedCardsCount();

	public Score getScore() {
		return Rule.newScore(hand);
	}

	public boolean canHit() {
		return hitStrategy().apply(hand);
	}

	public void hit(Card card) {
		hand.add(card);
	}

	public boolean isBust() {
		return Rule.isBust(hand);
	}

	public boolean isNotBust() {
		return Rule.isNotBust(hand);
	}

	public boolean isBlackJack() {
		return Rule.isBlackJack(hand);
	}

	public boolean isNotBlackJack() {
		return Rule.isNotBlackJack(hand);
	}

	public boolean hasBiggerScoreThan(Gamer other) {
		return getScore().isBiggerThan(other.getScore());
	}

	public boolean hasEqualScoreWith(Gamer other) {
		return getScore().isEqualTo(other.getScore());
	}

	public List<Card> firstOpenedCards() {
		return hand.getCards()
				.subList(ZERO, firstOpenedCardsCount());
	}

	public List<Card> getCards() {
		return hand.getCards();
	}

	public String getName() {
		return name.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Gamer gamer = (Gamer) o;
		return Objects.equals(name, gamer.name) &&
				Objects.equals(hand, gamer.hand);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hand);
	}
}
