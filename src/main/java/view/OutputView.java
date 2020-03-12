package view;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.result.GameResult;
import domain.result.Result;

public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printReceivedCards(List<Player> players, Dealer dealer) {
		System.out.println();

		printGiving(players, dealer);
		printCards(dealer);
		for (Player player : players) {
			printCards(player);
		}

		System.out.println();
	}

	private static void printGiving(List<Player> players, Dealer dealer) {
		StringBuilder sb = new StringBuilder();

		sb.append(dealer.getName())
			.append("와 ");
		sb.append(players.stream()
			.map(Player::getName)
			.collect(Collectors.joining(", ")));
		sb.append("에게 2장의 카드를 나누었습니다.");
		sb.append(NEW_LINE);
		System.out.println(sb);
	}

	public static void printCards(Participant user) {
		StringBuilder sb = new StringBuilder();
		sb.append(user.getName())
			.append(": ")
			.append(user.getCards()
				.stream()
				.map(Card::getCardInfo)
				.collect(Collectors.joining(", ")));

		System.out.println(sb);
	}

	public static void printDealerCards() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printDealerResult(TreeMap<Result, Integer> dealerResult) {
		System.out.println("## 최종승패");
		StringBuilder sb = new StringBuilder();
		sb.append("딜러: ");
		for (Result result : dealerResult.keySet()) {
			sb.append(dealerResult.get(result)).append(result.getResult());
		}
		System.out.println(sb);
	}

	public static void printPlayersResult(GameResult gameResult) {
		StringBuilder sb = new StringBuilder();
		Map<Player, Result> playersResult = gameResult.getPlayersResult();

		for (Map.Entry<Player, Result> playerResultEntry : playersResult.entrySet()) {
			sb.append(playerResultEntry.getKey().getName())
				.append(": ")
				.append(playerResultEntry.getValue().getResult())
				.append(NEW_LINE);
		}
		System.out.println(sb);
	}
}