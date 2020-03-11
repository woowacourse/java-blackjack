package view;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
		StringBuilder sb = new StringBuilder();
		sb.append(dealer.getName())
			.append("와 ");
		for (Player player : players) {
			sb.append(player.getName());
		}
		sb.append("에게 2장의 카드를 나누었습니다.");
		System.out.println(sb);
		printCards(dealer);
		for (Player player : players) {
			printCards(player);
		}
		System.out.println();
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

	public static void printDealerCards(Dealer dealer) {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
		printCards(dealer);
	}

	public static void printDealerResult(Map<Result, Long> dealerResult) {
		System.out.println("## 최종승패");
		StringBuilder sb = new StringBuilder();
		sb.append("딜러: ");
		if (Objects.nonNull(dealerResult.get(Result.WIN))) {
			sb.append(dealerResult.get(Result.WIN))
				.append("승 ");
		}
		if (Objects.nonNull(dealerResult.get(Result.DRAW))) {
			sb.append(dealerResult.get(Result.DRAW))
				.append("무 ");
		}
		if (Objects.nonNull(dealerResult.get(Result.LOSE))) {
			sb.append(dealerResult.get(Result.LOSE))
				.append("패");
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