package view;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.result.GameResult;
import domain.result.ResultType;
import domain.result.Score;

public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printGiving(Gamers gamers) {
		StringBuilder sb = new StringBuilder();

		sb.append(gamers.getDealer().getName())
				.append("와 ");
		sb.append(gamers.getPlayers()
				.stream()
				.map(Player::getName)
				.collect(Collectors.joining(", ")));
		sb.append("에게 2장의 카드를 나누었습니다.");
		sb.append(NEW_LINE);
		System.out.println(sb);
	}

	public static void printFirstOpenedCards(Gamers gamers) {
		for (Gamer gamer : gamers.getGamers()) {
			printGamerCard(gamer.getName(), gamer.firstOpenedCards());
		}
	}

	private static void printGamerCard(String name, List<Card> cards) {
		System.out.println(createCardFormat(name, cards));
	}

	private static String createCardFormat(String name, List<Card> cards) {
		StringBuilder sb = new StringBuilder();
		sb.append(name)
				.append(": ")
				.append(cards.stream()
						.map(Card::getCardInfo)
						.collect(Collectors.joining(", ")));
		return sb.toString();
	}

	public static void printCards(Gamer gamer) {
		printGamerCard(gamer.getName(), gamer.getCards());
	}

	public static void printDealerCards() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printCardsAndScore(Map<Gamer, Score> gamerToScore) {
		StringBuilder sb = new StringBuilder();
		for (Gamer gamer : gamerToScore.keySet()) {
			sb.append(createCardFormat(gamer.getName(), gamer.getCards()))
					.append(createScoreFormat(gamerToScore.get(gamer)));
		}
		System.out.println(sb);
	}

	private static String createScoreFormat(Score score) {
		StringBuilder sb = new StringBuilder();
		sb.append(" - 결과: ")
				.append(score.getScore())
				.append(NEW_LINE);
		return sb.toString();
	}

	public static void printDealerResult(Map<ResultType, Integer> dealerResult) {
		System.out.println("## 최종승패");
		StringBuilder sb = new StringBuilder();
		sb.append("딜러: ");
		for (ResultType resultType : dealerResult.keySet()) {
			sb.append(dealerResult.get(resultType)).append(resultType.getResult());
		}
		System.out.println(sb);
	}

	public static void printPlayersResult(Map<Player, ResultType> playersResult) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Player, ResultType> playerResultEntry : playersResult.entrySet()) {
			sb.append(playerResultEntry.getKey().getName())
					.append(": ")
					.append(playerResultEntry.getValue().getResult())
					.append(NEW_LINE);
		}
		System.out.println(sb);
	}
}