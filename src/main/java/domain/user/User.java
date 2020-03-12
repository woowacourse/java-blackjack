package domain.user;

import domain.Score;
import domain.card.Card;
import domain.card.Cards;
import domain.user.strategy.draw.DrawStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Comparable<User> {
	protected Cards cards;
	protected Score score;
	protected DrawStrategy drawStrategy;

	protected User() {
		this.cards = new Cards(new ArrayList<>());
		this.score = Score.of(this.cards);
	}

	public static User getInstance() {
		return new User();
	}

	public static User of(List<Card> cards) {
		User user = new User();
		cards.forEach(user::addCard);
		return user;
	}

	public void addCard(Card card) {
		this.cards.add(card);
		this.score = Score.of(cards);
	}

	public boolean canDrawMore() {
		return drawStrategy.canDraw(score);
	}

	public Cards openAllCards() {
		return this.cards;
	}

	public boolean isBlackJack() {
		return cards.hasInitialSize() && score.isBlackjackScore();
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
		return this.score.minus(other.score);
	}
}
