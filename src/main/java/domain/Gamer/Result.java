package domain.Gamer;

import domain.card.Card;

public class Result {
	public static int calculate(Player player) {
		return player.getCards()
			.stream()
			.mapToInt(Card::getCardNumber)
			.sum();
	}
}
