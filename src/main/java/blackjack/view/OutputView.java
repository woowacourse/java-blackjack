package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.GameReport;
import blackjack.player.Player;
import blackjack.player.Players;

public class OutputView {
	public void showReports(List<GameReport> gameReports) {

	}

	public void showCards(Players players) {
		List<Player> playersList = players.getPlayers();
		String names = playersList.stream()
			.map(Player::getName)
			.collect(Collectors.joining(", "));

		System.out.println(String.format("딜러와 %s에게 %d장을 나누었습니다.", names, playersList.size()));
		for (Player player : playersList) {
			String cardList = player.getCardBundle().stream()
				.map(card -> card.getNumber() + card.getSymbol())
				.collect(Collectors.joining(", "));

			System.out.println(String.format("%s: %s", player.getName(), cardList));
		}
	}

	public void showCards(Player player) {
		String cardList = player.getCardBundle().stream()
			.map(card -> card.getNumber() + card.getSymbol())
			.collect(Collectors.joining(", "));
		System.out.println(String.format("%s: %s", player.getName(), cardList));
	}

	public void showDealerCard(Player players2) {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}
}
