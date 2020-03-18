package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.result.ScoreBoard;
import domain.result.ScoreBoards;
import domain.result.UserResults;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class OutputView {
	private static final String JOINING_DELIMITER = ", ";
	private static final String SPACE = " ";
	private static final String NEW_LINE = System.lineSeparator();
	private static final String CARD_STRING_FORMAT = " 카드: %s  ";
	private static final String RESULT_CARD_SCORE_FORMAT = "- 결과: %d";

	private OutputView() {
	}

	public static void printInitialResult(List<Player> players, Dealer dealer) {
		printPlayersInitialDrawResult(players);
		printInitialDrawResult(dealer);
	}

	private static void printPlayersInitialDrawResult(List<Player> players) {
		for (Player player : players) {
			printInitialDrawResult(player);
		}
	}

	private static void printInitialDrawResult(User user) {
		System.out.printf("%s %s %s", user.getName(), parseCardsString(user.getFirstShowCards()), NEW_LINE);
	}

	public static void printPlayerCard(User user) {
		System.out.printf("%s %s %s", user.getName(), parseCardsString(user.getCards()), NEW_LINE);
	}

	public static void printDealerDraw() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printUsersCardsAndScore(ScoreBoards scoreBoards) {
		String allUsersResult = scoreBoards.getScoreBoards().stream()
			.map(OutputView::parseOneUserScore)
			.collect(Collectors.joining(NEW_LINE));

		System.out.println(allUsersResult);
	}

	private static String parseOneUserScore(ScoreBoard playerScoreBoard) {
		return String.format("%s %s %s",
			playerScoreBoard.getName(), parseCardsString(playerScoreBoard.getCards()),
			parseScore(playerScoreBoard.getScore()));
	}

	private static String parseCardsString(List<Card> cards) {
		String cardsAsString = cards.stream()
			.map(OutputView::parseCardString)
			.collect(Collectors.joining(JOINING_DELIMITER));

		return String.format(CARD_STRING_FORMAT, cardsAsString);
	}

	private static String parseCardString(Card card) {
		return card.getSymbolName() + card.getType();
	}

	private static String parseScore(int score) {
		return String.format(RESULT_CARD_SCORE_FORMAT, score);
	}

	public static void printGameResult(UserResults userResults) {
		System.out.println("## 최종 수익");
		String printData = userResults.getPlayerResults().stream()
			.map(playerPrize -> String.format("%s : %d원", playerPrize.getName(), playerPrize.getPrize()))
			.collect(Collectors.joining(NEW_LINE));

		System.out.println(printData);
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
