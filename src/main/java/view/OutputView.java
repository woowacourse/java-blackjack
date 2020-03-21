package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.score.Score;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;
import view.dto.UserPrizeDto;
import view.dto.UserScoreDto;

public class OutputView {
	private static final String JOINING_DELIMITER = ", ";
	private static final String NEW_LINE = System.lineSeparator();
	private static final String CARD_STRING_FORMAT = " 카드: %s  ";
	private static final String RESULT_CARD_SCORE_FORMAT = "- 결과: %s점";

	private OutputView() {
	}

	public static void printInitialDrawResult(Players players, Dealer dealer) {
		for (Player player : players) {
			System.out.printf("%s %s %s", player.getName(), parseCards(player.getFirstOpenCards()), NEW_LINE);
		}
		System.out.printf("%s %s %s", dealer.getName(), parseCards(dealer.getFirstOpenCards()), NEW_LINE);
	}

	public static void printPlayerCard(User user) {
		System.out.printf("%s %s %s", user.getName(), parseCards(user.getCards()), NEW_LINE);
	}

	public static void printDealerDraw(Dealer dealer) {
		System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%s", dealer.getName(), NEW_LINE);
	}

	public static void printUsersCardsAndScore(List<UserScoreDto> scoreBoards) {
		String parsedData = scoreBoards.stream()
			.map(OutputView::parseSingleUserCardAndScore)
			.collect(Collectors.joining(NEW_LINE));

		System.out.println(parsedData);
	}

	private static String parseSingleUserCardAndScore(UserScoreDto singleUserScore) {
		return String.format("%s %s %s",
			singleUserScore.getName(), parseCards(singleUserScore.getCards()),
			parseScore(singleUserScore.getScore()));
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

	public static void printGameResult(List<UserPrizeDto> userResults) {
		System.out.println("## 최종 수익");
		String parsedData = userResults.stream()
			.map(playerPrize -> String.format("%s : %s원", playerPrize.getName(), playerPrize.getPrize()))
			.collect(Collectors.joining(NEW_LINE));

		System.out.println(parsedData);
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
