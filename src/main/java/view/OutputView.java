package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.result.DealerResult;
import domain.result.MatchResult;
import domain.result.PlayerResults;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class OutputView {
	private static final String JOINING_DELIMITER = ", ";
	private static final String CARD_STRING_FORMAT = " 카드: %s  ";
	private static final String RESULT_CARD_SCORE_FORMAT = "- 결과: %d";
	private static final String DEALER_DRAW_ONE_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
	private static final String Colon = ":";
	private static final String SPACE = " ";
	private static final String PREFIX_DEALER_SCORE_RESULT_MESSAGE = "딜러: ";
	private static final String PREFIX_TOTAL_GAME_RESULT_MESSAGE = "## 최종 승패";
	private static final String NEW_LINE = System.lineSeparator();

	private OutputView() {
	}

	public static void printInitialResult(List<Player> players, Dealer dealer) {
		printPlayersInitialDrawResult(players);
		printDealerInitialDrawResult(dealer);
	}

	private static void printPlayersInitialDrawResult(List<Player> players) {
		StringBuilder builder = new StringBuilder();
		for (Player player : players) {
			builder.append(getUserInitialDrawResult(player));
		}
		System.out.println(builder);
	}

	private static void printDealerInitialDrawResult(Dealer dealer) {
		System.out.println(getUserInitialDrawResult(dealer));
	}

	private static String getUserInitialDrawResult(User user) {
		return String.format("%s %s %s", user.getName(),
			String.format(CARD_STRING_FORMAT, parseCardsString(user.getInitialCard())),
			NEW_LINE);
	}

	public static void printPlayerCard(User user) {
		StringBuilder builder = new StringBuilder();
		builder.append(user.getName());
		builder.append(String.format(CARD_STRING_FORMAT, parseCardsString(user.getCards())));
		builder.append(NEW_LINE);
		System.out.println(builder);
	}

	public static void printDealerDraw() {
		System.out.println(DEALER_DRAW_ONE_MORE_CARD_MESSAGE);
	}

	public static void printUserResult(List<User> users) {
		StringBuilder builder = new StringBuilder();
		for (User user : users) {
			builder.append(user.getName());
			builder.append(String.format(CARD_STRING_FORMAT, parseCardsString(user.getCards())));
			builder.append(String.format(RESULT_CARD_SCORE_FORMAT, user.calculateScore()));
			builder.append(NEW_LINE);
		}
		System.out.println(builder);
	}

	private static String parseCardsString(List<Card> cards) {
		return cards.stream()
			.map(OutputView::parseCardString)
			.collect(Collectors.joining(JOINING_DELIMITER));
	}

	private static String parseCardString(Card card) {
		return card.getTypeName() + card.getSymbol();
	}

	public static void printGameResult(PlayerResults playerResults, DealerResult dealerResult) {
		System.out.println(PREFIX_TOTAL_GAME_RESULT_MESSAGE);
		printDealerResult(dealerResult);
		printUsersResult(playerResults);
	}

	private static void printDealerResult(DealerResult dealerResult) {
		String result = Arrays.stream(MatchResult.values())
			.map(matchResult -> dealerResult.getResultCount(matchResult) + matchResult.getMatchResult())
			.collect(Collectors.joining(SPACE));
		System.out.println(result);
	}

	private static void printUsersResult(PlayerResults playerResults) {
		String result = playerResults.getPlayerResults().stream()
			.map(playerResult -> String.format("%s : %s", playerResult.getName(), playerResult.getMatchResult()))
			.collect(Collectors.joining(NEW_LINE));
		System.out.println(result);
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
