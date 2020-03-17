package blackjack.domain.user;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.exceptions.InvalidDeckException;
import blackjack.domain.exceptions.InvalidUserException;

// TODO: 2020-03-17 interface 분리
public abstract class User implements Comparable<User> {
	private static final int DRAW_LOWER_BOUND = 1;

	protected final String name;
	protected final Hand hand;

	User(String name, Hand hand) {
		validate(name);
		validate(hand);
		this.name = name.trim();
		this.hand = hand;
	}

	private void validate(Hand hand) {
		if (Objects.isNull(hand)) {
			throw new InvalidUserException(InvalidUserException.NULL_HAND);
		}
	}

	User(String name) {
		this(name, new Hand());
	}

	private void validate(String name) {
		if (Objects.isNull(name) || name.trim().isEmpty()) {
			throw new InvalidUserException(InvalidUserException.NULL_OR_EMPTY);
		}
	}

	public void draw(Deck deck) {
		validate(deck);
		hand.add(deck.draw());
	}

	private void validate(Deck deck) {
		if (Objects.isNull(deck)) {
			throw new InvalidDeckException(InvalidDeckException.NULL);
		}
	}

	public void draw(Deck deck, int drawNumber) {
		validate(drawNumber);
		IntStream.range(0, drawNumber)
			.forEach(e -> draw(deck));
	}

	protected void validate(int drawNumber) {
		if (drawNumber < DRAW_LOWER_BOUND) {
			throw new InvalidUserException(InvalidUserException.INVALID_DRAW_NUMBER);
		}
	}

	public abstract boolean canDraw();

	public String getName() {
		return name;
	}

	public abstract List<Card> getInitialDealtHand();

	public List<Card> getHand() {
		return hand.getCards();
	}

	public int getScore() {
		return hand.calculateScore().getScore();
	}

	private int getBustHandledScore() {
		return hand.calculateBustHandledScore().getScore();
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
