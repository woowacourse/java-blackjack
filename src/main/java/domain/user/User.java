package domain.user;

import domain.ScoreType;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.user.strategy.draw.DrawStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
	private static final int BLACKJACK_SCORE = 21;
	private static final int INITIAL_HANDS_SIZE = 2;

	protected Cards hands;
	protected int score;
	protected DrawStrategy drawStrategy;

	protected User() {
		this.hands = new Cards(new ArrayList<>());
		this.score = 0;
	}

	public static User getInstance() {
		return new User();
	}

	public static User of(List<Card> cards) {
		User user = new User();
		cards.forEach(card -> {
			user.hands.add(card);
			user.score = ScoreType.getScoreOf(user.hands);
		});
		return user;
	}

	public void proceedInitialPhase(Deck deck) {
		for (int i = 0; i < INITIAL_HANDS_SIZE; i++) {
			hands.add(deck.pop());
			score = ScoreType.getScoreOf(hands);
		}
	}

	public boolean isNotBlackJack() {
		return hands.isNotInitialSize() || score != BLACKJACK_SCORE;
	}

	public boolean canDrawMore() {
		return drawStrategy.canDraw(score);
	}


	public void receive(Card card) {
		hands.add(card);
		score = ScoreType.getScoreOf(hands);
	}

	public Cards openAllCards() {
		return hands;
	}

	public int getScoreMinusBy(User compared) {
		return score - compared.score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User)o;
		return Objects.equals(hands, user.hands);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hands);
	}
}
