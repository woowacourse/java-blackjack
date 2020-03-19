package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.result.ScoreBoard;
import domain.result.ScoreBoards;
import domain.result.UserResults;
import domain.score.Score;
import domain.user.Dealer;
import domain.user.User;

public class OutputView {
	private static final String JOINING_DELIMITER = ", ";
	private static final String NEW_LINE = System.lineSeparator();
	private static final String CARD_STRING_FORMAT = " 카드: %s  ";
	private static final String RESULT_CARD_SCORE_FORMAT = "- 결과: %s점";

	private OutputView() {
	}

	public static void printInitialDrawResult(User user) {
		System.out.printf("%s %s %s", user.getName(), parseCards(user.getFirstOpenCards()), NEW_LINE);
	}

	public static void printPlayerCard(User user) {
		System.out.printf("%s %s %s", user.getName(), parseCards(user.getCards()), NEW_LINE);
	}

	public static void printDealerDraw(Dealer dealer) {
		System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%s", dealer.getName(), NEW_LINE);
	}

	public static void printUsersCardsAndScore(ScoreBoards scoreBoards) {
		System.out.println(parseAllUsersCardAndScoreData(scoreBoards));
	}

	private static String parseAllUsersCardAndScoreData(ScoreBoards scoreBoards) {
		return scoreBoards.getScoreBoards().stream()
			.map(OutputView::parseSingleUserCardAndScore)
			.collect(Collectors.joining(NEW_LINE));
	}

	private static String parseSingleUserCardAndScore(ScoreBoard playerScoreBoard) {
		return String.format("%s %s %s",
			playerScoreBoard.getName(), parseCards(playerScoreBoard.getCards()),
			parseScore(playerScoreBoard.getScore()));
	}

	private static String parseCards(List<Card> cards) {
		String cardsAsString = cards.stream()
			.map(OutputView::parseCardString)
			.collect(Collectors.joining(JOINING_DELIMITER));

		return String.format(CARD_STRING_FORMAT, cardsAsString);
	}

	private static String parseCardString(Card card) {
		return card.getSymbolName() + card.getType();
	}

	private static String parseScore(Score score) {
		return String.format(RESULT_CARD_SCORE_FORMAT, score);
	}

	public static void printGameResult(UserResults userResults) {
		System.out.println("## 최종 수익");
		System.out.println(parseAllUsersPrizeResult(userResults));
	}

	private static String parseAllUsersPrizeResult(UserResults userResults) {
		return userResults.getPlayerResults().stream()
			.map(playerPrize -> String.format("%s : %s원", playerPrize.getName(), playerPrize.getPrize()))
			.collect(Collectors.joining(NEW_LINE));
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
