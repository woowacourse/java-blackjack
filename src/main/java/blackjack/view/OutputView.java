package blackjack.view;

import static java.util.stream.Collectors.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;

public class OutputView {

	private static final String PRINT_OPEN_CARD_PREFIX_MESSAGE = "\n%s와 ";
	private static final String PRINT_OPEN_CARD_SUFFIX_MESSAGE = "에게 2장의 카드를 나누었습니다.\n";
	private static final String PRINT_JOINING_DELIMITER = ", ";
	private static final String PRINT_DEFAULT_FORMAT_MESSAGE = "%s: %s\n";
	private static final String PRINT_SHOW_CARD_FORMAT_MESSAGE = "%s카드: %s\n";
	private static final String PRINT_DEALER_RECEIVE_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
	private static final String PRINT_DEALER_NOT_RECEIVE_CARD = "\n딜러는 17이상이라 한장의 카드를 더 받지 못했습니다.\n";
	private static final String PRINT_FINAL_CARD_RESULT = "%s카드: %s - 결과: %d\n";
	private static final String PRINT_BLANK = " ";
	private static final String FINAL_RESULT_MESSAGE = "\n## 최종 승패";

	public static void printErrorMessage(final String message) {
		System.out.println(message);
	}

	public static void printOpenCards(final List<Gamer> gamers, final Player dealer) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format(PRINT_OPEN_CARD_PREFIX_MESSAGE, Dealer.DEALER_NAME))
			.append(joinNames(gamers))
			.append(PRINT_OPEN_CARD_SUFFIX_MESSAGE);

		appendDealerFormat(dealer, stringBuilder);
		appendGamerFormat(gamers, stringBuilder);

		System.out.println(stringBuilder);
	}

	private static String joinNames(final List<Gamer> gamers) {
		return gamers.stream()
			.map(Gamer::getName)
			.collect(joining(PRINT_JOINING_DELIMITER));
	}

	private static void appendDealerFormat(final Player dealer, final StringBuilder stringBuilder) {
		List<Card> cards = dealer.openCards();
		stringBuilder.append(String.format(PRINT_DEFAULT_FORMAT_MESSAGE, Dealer.DEALER_NAME, joinCards(cards)));
	}

	private static String joinCards(final List<Card> cards) {
		return cards.stream()
			.map(Card::getName)
			.collect(joining(PRINT_JOINING_DELIMITER));
	}

	private static void appendGamerFormat(final List<Gamer> gamers, final StringBuilder stringBuilder) {
		for (Gamer gamer : gamers) {
			stringBuilder.append(String.format(PRINT_SHOW_CARD_FORMAT_MESSAGE,
				gamer.getName(),
				joinCards(gamer.openCards())));
		}
	}

	public static void printGamerCards(final Gamer gamer) {
		System.out.printf(PRINT_SHOW_CARD_FORMAT_MESSAGE,
			gamer.getName(),
			joinCards(gamer.showCards()));
	}

	public static void printDealerReceive(final boolean receivable) {
		if (receivable) {
			System.out.println(PRINT_DEALER_RECEIVE_CARD);
			return;
		}
		System.out.println(PRINT_DEALER_NOT_RECEIVE_CARD);
	}

	public static void printFinalResult(final Player dealer, final List<Gamer> gamers) {
		printDealerCardsResult(dealer);
		gamers.forEach(OutputView::printGamerCardsResult);
	}

	private static void printDealerCardsResult(final Player dealer) {
		System.out.printf(PRINT_FINAL_CARD_RESULT,
			Dealer.DEALER_NAME,
			joinCards(dealer.showCards()),
			dealer.calculateResult());
	}

	private static void printGamerCardsResult(final Gamer gamer) {
		System.out.printf(PRINT_FINAL_CARD_RESULT,
			gamer.getName(),
			joinCards(gamer.showCards()),
			gamer.calculateResult());
	}

	public static void printFinalResultBoard(final Map<Gamer, Result> gamerResultBoard) {
		System.out.println(FINAL_RESULT_MESSAGE);

		System.out.print(dealerPointToString(gamerResultBoard));
		gamerResultBoard.forEach((key, value) -> System.out.printf(PRINT_DEFAULT_FORMAT_MESSAGE,
			key.getName(),
			value.getResult()));
	}

	private static String dealerPointToString(final Map<Gamer, Result> gamerResultBoard) {
		return String.format(PRINT_DEFAULT_FORMAT_MESSAGE, Dealer.DEALER_NAME,
			calculateDealerResultBoard(gamerResultBoard).entrySet().stream()
				.map(board -> dealerResultToString(board.getKey(),
					board.getValue()))
				.collect(joining(PRINT_BLANK)));
	}

	private static Map<Result, Integer> calculateDealerResultBoard(final Map<Gamer, Result> gamerResultBoard) {
		Map<Result, Integer> enumMap = new EnumMap<>(Result.class);
		for (Result result : gamerResultBoard.values()) {
			Result dealerResult = Result.convertToDealerResult(result);
			enumMap.put(dealerResult, enumMap.getOrDefault(dealerResult, 0) + 1);
		}
		return enumMap;
	}

	private static String dealerResultToString(final Result result, final int value) {
		return value + result.getResult();
	}

}
