package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.betting.Money;
import domain.card.Card;
import domain.card.CardDivider;
import domain.card.Cards;

public abstract class User {
	protected final Cards cards;

	public User() {
		this.cards = new Cards(new ArrayList<>());
	}

	public void addCards(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public void drawFirst(CardDivider cardDivider) {
		this.cards.addAll(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
	}

	public void draw(CardDivider cardDivider) {
		this.cards.add(cardDivider.divide());
	}

	public int getCardsSize() {
		return cards.size();
	}

	public int calculateScore() {
		return cards.calculateScore();
	}

	public List<Card> getCards() {
		return cards.getCards();
	}

	public abstract List<Card> getInitialCard();

	public abstract String getName();
}
