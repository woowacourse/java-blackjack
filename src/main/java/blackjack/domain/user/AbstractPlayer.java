package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;

import java.util.List;
import java.util.Objects;

public abstract class AbstractPlayer implements Playable {
	protected static final int MAX_SCORE = 21;

	private final Name name;
	private final Hand hand;

	protected AbstractPlayer(Name name) {
		this.name = name;
		this.hand = new Hand();
	}

	@Override
	public void receiveCard(Card card) {
		hand.add(card);
	}

	@Override
	public void receiveCards(List<Card> cards) {
		hand.addAll(cards);
	}

	@Override
	public boolean isBust() {
		return computeScore().isOver(MAX_SCORE);
	}

	@Override
	public int countCards() {
		return hand.size();
	}

	@Override
	public Score computeScore() {
		return hand.computeScore();
	}

	@Override
	public boolean isBlackjack() {
		return hand.hasOnlyTwoCards() && computeScore().equals(Score.of(MAX_SCORE));
	}

	@Override
	public List<Card> getHand() {
		return hand.getHand();
	}

	@Override
	public Name getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractPlayer that = (AbstractPlayer) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(hand, that.hand);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hand);
	}

	@Override
	public String toString() {
		return "AbstractPlayer{" +
				"name=" + name +
				", hand=" + hand +
				'}';
	}
}
