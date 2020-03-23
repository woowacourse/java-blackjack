package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import blackjack.domain.exceptions.InvalidHandException;
import blackjack.domain.result.ResultScore;

public class Hand {
	private final List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void add(Card card) {
		validate(card);
		cards.add(card);
	}

	private void validate(Card card) {
		if (Objects.isNull(card)) {
			throw new InvalidHandException(InvalidHandException.NULL);
		}
	}

	public void add(List<Card> cards) {
		validate(cards);
		this.cards.addAll(cards);
	}

	private void validate(List<Card> cards) {
		if (Objects.isNull(cards) || cards.isEmpty()) {
			throw new InvalidHandException(InvalidHandException.EMPTY);
		}
	}

	public ResultScore calculateResultScore() {
		return ResultScore.of(cards);
	}

	public List<Card> getCards() {
		return cards;
	}
}
