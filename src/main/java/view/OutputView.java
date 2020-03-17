package view;

import domain.card.Card;
import domain.gamer.*;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
	private static final String DELIMITER = ",";
	private static final String NEWLINE = "\n";

	public static void printPlayerNamesGuide() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
	}

	public static void printInitCardGuide(Gamers gamers) {
		StringBuffer initCardGuide = new StringBuffer();
		initCardGuide.append(NEWLINE)
			.append("딜러와 ")
			.append(gamers.getPlayers()
				.stream()
				.map(Gamer::getName)
				.collect(Collectors.joining(DELIMITER)))
			.append(" 에게 카드 2장을 나누었습니다.");
		System.out.println(initCardGuide.toString());
	}

	public static void printGamersCard(Gamers gamers) {
		System.out.println(printDealerCard(gamers.getDealer()));
		gamers.getPlayers().forEach(OutputView::printGamerCard);
		System.out.println();
	}

	public static void printGamerCard(Gamer gamer) {
		System.out.println(printCards(gamer));
	}

	private static String printDealerCard(Dealer dealer) {
		StringBuilder cardsToString = new StringBuilder();
		cardsToString.append(dealer.getName())
			.append(" : ")
			.append(printCard(dealer.getCards().get(0)));
		return cardsToString.toString();
	}

	private static String printCards(Gamer gamer) {
		StringBuilder cardsToString = new StringBuilder();
		cardsToString.append(gamer.getName())
			.append(" : ")
			.append(gamer.getCards()
				.stream()
				.map(OutputView::printCard)
				.collect(Collectors.joining(", ")));
		return cardsToString.toString();
	}

	private static String printCard(Card card) {
		StringBuilder cardToString = new StringBuilder();
		cardToString.append(card.getCardNumber().getCardInitial())
			.append(card.getCardSuit().getSuit());
		return cardToString.toString();
	}

	public static void printAddCardAtDealer() {
		System.out.println("딜러는 17이상이 될 때까지 카드를 더 받았습니다.");
	}

	public static void printAddCardGuide(Player player) {
		System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%s", player.getName(), NEWLINE);
	}

	public static void printGameResults(Gamers gamers) {
		printCardsResultAndScore(gamers);
		printPlayersWinOrLose(gamers.generateGameResults());
	}

	private static void printCardsResultAndScore(Gamers gamers) {
		System.out.println();
		System.out.printf("%s - 결과 : %s" + NEWLINE, printCards(gamers.getDealer()),
			gamers.getDealer().calculateScore());
		gamers.getPlayers()
			.forEach(player -> System.out.printf("%s - 결과 : %s" + NEWLINE, printCards(player),
				player.calculateScore()));
	}

	private static void printPlayersWinOrLose(GameResult gameResult) {
		StringBuilder dealerResult = new StringBuilder();
		dealerResult.append(NEWLINE)
			.append("## 최종승패")
			.append(NEWLINE)
			.append("딜러 : ")
			.append(printResultCount(gameResult, MatchResult.LOSE, MatchResult.WIN.getInitial()))
			.append(printResultCount(gameResult, MatchResult.DRAW, MatchResult.DRAW.getInitial()))
			.append(printResultCount(gameResult, MatchResult.WIN, MatchResult.LOSE.getInitial()));
		System.out.println(dealerResult.toString());
		gameResult.getGameResult().forEach((a, b) -> System.out.println(a.getName() + " : " + b.getInitial()));
	}

	private static String printResultCount(GameResult gameResult, MatchResult matchResult,
		String dealerInitial) {
		StringBuilder winOrLoseAndCount = new StringBuilder();
		winOrLoseAndCount.append(gameResult.getGameResult()
			.values()
			.stream()
			.filter(x -> x == matchResult)
			.count())
			.append(dealerInitial);
		return winOrLoseAndCount.toString();
	}
}
