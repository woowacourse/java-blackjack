package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.card.domain.Card;
import blackjack.player.domain.Dealer;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Player;
import blackjack.player.domain.Players;
import blackjack.view.dto.GameStatisticsDTO;

public class OutputView {
	public static void showCards(Players players) {
		List<Player> gamblers = players.findGamblers();
		Dealer dealer = (Dealer)players.findDealer();
		Card dealerCard = dealer.getFirstCard();

		System.out.println(
			String.format("딜러와 %s에게 %d장을 나누었습니다."
				, collectGamblersNames(gamblers)
				, Players.STARTING_CARD_COUNT)
		);
		System.out.println(String.format("딜러: %s%s", dealerCard.getNumber(), dealerCard.getSymbol()));
		gamblers.forEach(gambler -> System.out.println(getCardInfo(gambler)));
	}

	private static String collectGamblersNames(List<Player> gamblers) {
		return gamblers.stream()
			.map(player -> ((Gambler)player).getName())
			.collect(Collectors.joining(", "));
	}

	public static void showCardInfo(Player player) {
		System.out.println(getCardInfo(player));
	}

	private static String getCardInfo(Player player) {
		return String.format("%s: %s", player.getName(), makeCardInfo(player));
	}

	private static String makeCardInfo(Player player) {
		return player.getCardBundle().stream()
			.map(card -> String.format("%s%s", card.getMessage(), card.getSymbol()))
			.collect(Collectors.joining(", "));
	}

	private static String getCardList(Players players) {
		List<Player> gamePlayers = players.getPlayers();
		return gamePlayers.stream()
			.map(player -> String.format("%s 결과 - %d", getCardInfo(player), player.getScore()))
			.collect(Collectors.joining(System.lineSeparator()));
	}

	public static void showDealerDrawMessage() {
		System.out.println(String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.\n", Dealer.HIT_VALUE));
	}

	public static void showReports(Players players) {
		showFinalPlayersCards(players);
		GameStatisticsDTO gameStatisticsDTO = new GameStatisticsDTO(players.getReports());
		System.out.println("\n## 최종 수익");
		System.out.println(gameStatisticsDTO.getDealerResult());
		System.out.println(gameStatisticsDTO.getGamblerResult());
	}

	private static void showFinalPlayersCards(Players players) {
		System.out.println(getCardList(players));
	}
}
