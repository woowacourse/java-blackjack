package blackjack.domain.dto;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class CardsDto {
	private final List<Card> cards;

	private CardsDto(List<Card> cards) {
		this.cards = cards;
	}

	public static CardsDto from(Cards cards) {
		return new CardsDto(cards.getCards());
	}

	public List<Card> getCards() {
		return cards;
	}
}
