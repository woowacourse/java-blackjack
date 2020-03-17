package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.result.GameResult;
import domain.result.Profit;
import domain.result.ResultType;
import domain.result.Score;

public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();
	private static final int FIRST_CARD = 0;

	public static void printGiving(List<Player> players, Dealer dealer) {
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

	public static void printDealerCard(Dealer dealer) {
		StringBuilder sb = new StringBuilder();
		sb.append(dealer.getName())
			.append(": ")
			.append(dealer.getCards().get(FIRST_CARD).getCardInfo());
		System.out.println(sb);
	}

	public static void printPlayersCard(List<Player> players) {
		System.out.println();
		for (Player player : players) {
			printCards(player);
		}
		System.out.println();
	}

	public static void printCards(Gamer user) {
		StringBuilder sb = createCardsFormat(user);
		System.out.println(sb);
	}

	private static StringBuilder createCardsFormat(Gamer user) {
		StringBuilder sb = new StringBuilder();
		sb.append(user.getName())
			.append(": ")
			.append(user.getCards()
				.stream()
				.map(Card::getCardInfo)
				.collect(Collectors.joining(", ")));
		return sb;
	}

	public static void printDealerCards() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printCardsScore(GameResult gameResult) {
		StringBuilder sb = new StringBuilder();
		Map<Gamer, Score> scores = gameResult.getScores();
		for (Map.Entry<Gamer, Score> gamerScoreEntry : scores.entrySet()) {
			createScoreFormat(gamerScoreEntry.getKey(), gamerScoreEntry.getValue(), sb);
		}
		System.out.println(sb);
	}

	private static void createScoreFormat(Gamer gamer, Score score, StringBuilder sb) {
		sb.append(createCardsFormat(gamer))
			.append(" - 결과: ")
			.append(score.getScore())
			.append(NEW_LINE);
	}

	public static void printDealerResult(Profit dealerProfit) {
		System.out.println("## 최종승패");
		StringBuilder sb = new StringBuilder();
		sb.append("딜러: ");
		sb.append(dealerProfit.getProfit());
		System.out.println(sb);
	}

	public static void printPlayersResult(GameResult gameResult) {
		StringBuilder sb = new StringBuilder();
		Map<Player, Profit> playersResult = gameResult.getPlayerToProfit();

		for (Map.Entry<Player, Profit> playerResultEntry : playersResult.entrySet()) {
			sb.append(playerResultEntry.getKey().getName())
				.append(": ")
				.append(playerResultEntry.getValue().getProfit())
				.append(NEW_LINE);
		}
		System.out.println(sb);
	}
}