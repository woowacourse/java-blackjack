package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.player.card.Card;

public class Bundles {

	private Bundles() {
	}

	public static String getCardList(Players players) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Player> gamePlayers = players.getPlayers();
		for (Player player : gamePlayers) {
			List<Card> cardBundle = player.getCardBundle();
			String cardList = cardBundle.stream()
				.map(card -> String.format("%s%s", card.getMessage(), card.getSymbol()))
				.collect(Collectors.joining(", "));
			stringBuilder.append(player.getName())
				.append(": ")
				.append(cardList)
				.append("결과 - ")
				.append(player.getScore()).append(System.lineSeparator());
		}
		return stringBuilder.toString();
	}

}
