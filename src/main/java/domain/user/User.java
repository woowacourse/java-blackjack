package domain.user;

import domain.card.Card;
import domain.card.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
	protected Cards cards;

	protected User() {
		this.cards = new Cards(new ArrayList<>());
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
	}

	public Cards openAllCards() {
		return this.cards;
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
}
