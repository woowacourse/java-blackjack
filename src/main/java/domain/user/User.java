package domain.user;

import domain.ScoreType;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.user.strategy.draw.DrawStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Comparable<User> {
	public static final String NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE = "null이 인자로 올 수 없습니다.";
	private static final int BLACKJACK_SCORE = 21;

	protected Cards cards;
	protected int score;
	protected DrawStrategy drawStrategy;

	protected User() {
		this.cards = new Cards(new ArrayList<>());
		this.score = ScoreType.of(this.cards);
	}

	public static User getInstance() {
		return new User();
	}

	public static User of(List<Card> cards) {
		Objects.requireNonNull(cards, NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE);
		User user = new User();
		cards.forEach(user::addCard);
		return user;
	}

	public void addCard(Card card) {
		Objects.requireNonNull(card, NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE);
		this.cards.add(card);
		this.score = ScoreType.of(this.cards);
	}

	public void addInitialCards(Deck deck) {
		addCard(deck);
		addCard(deck);
	}

	public void addCard(Deck deck) {
		Objects.requireNonNull(deck, NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE);
		this.cards.add(deck.pop());
		this.score = ScoreType.of(this.cards);
	}

	public Cards openAllCards() {
		return this.cards;
	}

	public boolean canDrawMore() {
		return drawStrategy.canDraw(score);
	}

	public boolean isNotBlackjack() {
		return !(cards.hasInitialSize() && isBlackjackScore());
	}

	public boolean isBlackjackScore() {
		return score == BLACKJACK_SCORE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(cards, user.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}

	@Override
	public int compareTo(User other) {
		return this.score - other.score;
	}
}
