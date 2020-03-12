package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.GameReport;
import blackjack.player.Player;
import blackjack.player.Players;

public class OutputView {
	public void showReports(List<GameReport> gameReports) {
		GameStatistics gameStatistics = new GameStatistics(gameReports);
		System.out.println("## 최종 승패");
		System.out.println(gameStatistics.getDealerResult());
		System.out.println(gameStatistics.getGamblerResult());
	}

	public void showPlayersCards(Players players) {
		System.out.println(Bundles.getCardList(players));
	}

	public void showCards(Players players) {
		List<Player> playersList = players.getPlayers();
		String gamblerNames = playersList.stream()
			.filter(Player::isGambler)
			.map(Player::getName)
			.collect(Collectors.joining(", "));

		System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", gamblerNames));
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
