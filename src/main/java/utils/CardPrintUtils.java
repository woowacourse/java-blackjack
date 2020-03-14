package utils;

import domain.card.PlayerCards;
import domain.card.cardfactory.Card;
import domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class CardPrintUtils {
	private static final String STRING_FORMAT_PRINT_CARD = "%s카드 : %s";
	private static final String CARD_DELIMITER = ", ";

	public static String formatNameAndOneCard(Player player) {
		PlayerCards playerCards = player.getPlayerCards();
		return String.format(STRING_FORMAT_PRINT_CARD, player.getName(), toStringOneCard(playerCards));
	}

	public static String formatNameAndAllCard(Player player) {
		PlayerCards playerCards = player.getPlayerCards();
		return String.format(STRING_FORMAT_PRINT_CARD, player.getName(), toStringAllCard(playerCards));
	}

	public static String toStringOneCard(PlayerCards playerCards) {
		Card oneCard = playerCards.bringOneCard();
		return oneCard.toString();
	}

	public static String toStringAllCard(PlayerCards playerCards) {
		List<Card> cards = playerCards.getPlayerCards();
		List<String> cardNames = cards.stream()
				.map(Card::toString)
				.collect(Collectors.toList());
		return String.join(CARD_DELIMITER, cardNames);
	}
}
