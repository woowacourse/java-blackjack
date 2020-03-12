package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.hand.Hand;

public abstract class User {
	private static final int DRAW_LOWER_BOUND = 1;
	private static final int BLACKJACK_SCORE = 21;

	protected final String name;
	protected final Hand hand;

	public User(String name) {
		validate(name);
		this.name = name;
		this.hand = new Hand();
	}

	private void validate(String name) {
		if (Objects.isNull(name) || name.trim().isEmpty()) {
			throw new InvalidUserException(InvalidUserException.NULL_OR_EMPTY);
		}
	}

	public void draw(Deck deck) {
		hand.add(deck.draw());
	}

	public void draw(Deck deck, int drawNumber) {
		validateDrawNumber(drawNumber);
		List<Card> drawCards = IntStream.range(0, drawNumber)
			.mapToObj(e -> deck.draw())
			.collect(toList());
		hand.add(drawCards);
	}

	protected void validateDrawNumber(int drawNumber) {
		if (drawNumber < DRAW_LOWER_BOUND) {
			throw new InvalidUserException(InvalidUserException.INVALID_DRAW_NUMBER);
		}
	}

	public boolean isBust() {
		return hand.calculateScore().isMoreThan(BLACKJACK_SCORE);
	}

	abstract boolean canDraw();
}
