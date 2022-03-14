package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.dto.DealerResultDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.domain.dto.ResultDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
	private static final String DEALER_PLAYERS_DELIMITER = "와 ";
	private static final String CARD_DELIMITER = ", ";
	private static final String INIT_CARD_MESSAGE = "에게 2장의 카드를 나누었습니다.";
	private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
	private static final String SCORE_MESSAGE = " - 결과: ";
	private static final String RESULT_MESSAGE = "## 최종 승패";
	private static final String COLON = ": ";
	private static final String BLANK = " ";

	public static void printInitStatus(Dealer dealer, List<Player> players) {
		System.out.println();
		List<String> playerNames = players.stream()
			.map(Participant::getName)
			.collect(Collectors.toList());

		System.out.println(dealer.getName() + DEALER_PLAYERS_DELIMITER
			+ String.join(CARD_DELIMITER, playerNames) + INIT_CARD_MESSAGE);

		printInitCards(dealer, players);
	}

	private static void printInitCards(Dealer dealer, List<Player> players) {
		System.out.println(makeStatusFormat(dealer.getName(), dealer.getMyCards()));

		players.forEach(player ->
			System.out.println(makeStatusFormat(player.getName(), player.getMyCards())));
		System.out.println();
	}

	private static String makeStatusFormat(String name, List<Card> cards) {
		List<String> cardNames = cards.stream()
			.map(Card::getName)
			.collect(Collectors.toList());

		return name + COLON + String.join(CARD_DELIMITER, cardNames);
	}

	public static void printCards(Player player) {
		System.out.println(makeStatusFormat(player.getName(), player.getMyCards()));
	}

	public static void printDealerAdditionalCard() {
		System.out.println();
		System.out.println(DEALER_DRAW_MESSAGE);
	}

	public static void printCardsAndResult(Dealer dealer, List<Player> players) {
		System.out.println();
		System.out.println(makeStatusFormat(dealer.getName(), dealer.getMyCards()) +
			printScoreResult(dealer.score()));

		for (Player player : players) {
			System.out.println(makeStatusFormat(player.getName(), player.getMyCards())
				+ printScoreResult(player.score()));
		}
	}

	private static String printScoreResult(int score) {
		return (SCORE_MESSAGE + score);
	}

	public static void printResult(ResultDto resultDto) {
		System.out.println();
		System.out.println(RESULT_MESSAGE);
		printDealerResult(resultDto.getDealerResultDto());
		printPlayerResult(resultDto.getPlayerResultDto());
	}

	private static void printDealerResult(DealerResultDto dealerResultDto) {
		System.out.print(dealerResultDto.getName() + COLON);

		Map<String, Integer> outcome = dealerResultDto.getOutcome();
		outcome.keySet()
			.forEach(record -> printNotZeroRecordForDealer(outcome, record));

		System.out.println();
	}

	private static void printNotZeroRecordForDealer(Map<String, Integer> outcome, String record) {
		if(outcome.get(record) > 0) {
			System.out.print(outcome.get(record) + record + BLANK);
		}
	}

	private static void printPlayerResult(PlayerResultDto playerResultDto) {
		Map<String, String> outcome = playerResultDto.getOutcome();
		outcome.keySet()
			.forEach(player -> System.out.println(player + COLON + outcome.get(player)));
	}
}
