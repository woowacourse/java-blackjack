package blackjack.dto;

import blackjack.domain.card.Card;

public record CardDto(String cardNumber, String cardShape) {

	public static CardDto fromCardEntity(Card card) {
		String name = card.cardNumber().getName();
		String shape = card.cardShape().getName();

		return new CardDto(name, shape);
	}
}
