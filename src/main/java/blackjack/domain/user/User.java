package blackjack.domain.user;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.exceptions.InvalidDeckException;
import blackjack.domain.exceptions.InvalidUserException;

public abstract class User implements Comparable<User>, BlackjackParticipant {
	private static final int DRAW_LOWER_BOUND = 1;

	protected final String name;
	protected final Hand hand;

	User(String name, Hand hand) {
		validate(name);
		validate(hand);
		this.name = name.trim();
		this.hand = hand;
	}

	User(String name) {
		this(name, new Hand());
	}

	private void validate(String name) {
		if (Objects.isNull(name) || name.trim().isEmpty()) {
			throw new InvalidUserException(InvalidUserException.NULL_OR_EMPTY);
		}
	}

	private void validate(Hand hand) {
		if (Objects.isNull(hand)) {
			throw new InvalidUserException(InvalidUserException.NULL_HAND);
		}
	}

	@Override
	public void hit(Deck deck) {
		validate(deck);
		hand.add(deck.draw());
	}

	private void validate(Deck deck) {
		if (Objects.isNull(deck)) {
			throw new InvalidDeckException(InvalidDeckException.NULL);
		}
	}

	@Override
	public void hit(Deck deck, int drawNumber) {
		validate(drawNumber);
		IntStream.range(0, drawNumber)
			.forEach(e -> hit(deck));
	}

	protected void validate(int drawNumber) {
		if (drawNumber < DRAW_LOWER_BOUND) {
			throw new InvalidUserException(InvalidUserException.INVALID_DRAW_NUMBER);
		}
	}

	@Override
	public abstract boolean canDraw();

	@Override
	public abstract List<Card> getInitialDealtHand();

	@Override
	public int getScore() {
		return hand.calculateScore().getScore();
	}

	@Override
	public int getBustHandledScore() {
		return hand.calculateBustHandledScore().getScore();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Card> getHand() {
		return hand.getCards();
	}

	@Override
	public int compareTo(User that) {
		return Integer.compare(this.getBustHandledScore(), that.getBustHandledScore());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User)o;
		return Objects.equals(name, user.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hand);
	}
}
