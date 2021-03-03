package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.name.Name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
	public static final int ACE_ADDITIONAL_VALUE = 10;
	public static final int BLACKJACK_VALUE = 21;

	protected final Name name;
	protected final List<Card> cards = new ArrayList<>();

	protected Participant(final Name name) {
		this.name = name;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public void receiveCard(Card card) {
		cards.add(card);
	}

	public String getName() {
		return name.getName();
	}


	public int calculateScore() {
		int score = calculateMinimumPossibleScore();
		int aceCount = calculateAceCount();

		for (int i = 0; i < aceCount; i++) {
			score = getNextPossibleScore(score);
		}
		return score;
	}

	private int getNextPossibleScore(int sum) {
		if (sum + ACE_ADDITIONAL_VALUE <= BLACKJACK_VALUE) {
			return sum + ACE_ADDITIONAL_VALUE;
		}
		return sum;
	}

	private int calculateAceCount() {
		return (int) cards.stream()
				.filter(Card::isAce)
				.count();
	}

	private int calculateMinimumPossibleScore() {
		return cards.stream()
				.mapToInt(Card::getCardNumberValue)
				.sum();
	}

	public boolean isScoreLowerThanBlackJackValue() {
		return calculateScore() < BLACKJACK_VALUE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Participant that = (Participant) o;
		return Objects.equals(name, that.name) && Objects.equals(cards, that.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, cards);
	}
}
