package blackjack.domain.result;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;

public class CardResult {

	private final String name;
	private final List<Card> cards;
	private final int sumOfCards;

	public CardResult(Gamer gamer) {
		this.name = gamer.getName();
		this.cards = gamer.getCards();
		this.sumOfCards = gamer.sumCardsNumber();
	}

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getSumOfCards() {
		return sumOfCards;
	}
}
