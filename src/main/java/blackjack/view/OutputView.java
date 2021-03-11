package blackjack.view;

import static blackjack.domain.participant.Dealer.*;

import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class OutputView {
	public static final String COLON_DELIMITER = ": ";
	public static final String RESULT_DELIMITER = " - 결과: ";
	private static final int INITIAL_DRAWING_COUNT = 2;
	private static final String COMMA_DELIMITER_TO_PRINT = ", ";
	private static final int COUNT_OF_DEALER_OPENING_CARDS = 1;
	private static final String NOTICE_DRAWING_CARDS = "%s와 %s에게 %d장씩 나누었습니다.\n";
	private static final String NOTICE_FINAL_PROFIT = "## 최종 수익";
	private static final String NOTICE_DEALER_RECEIVE = "딜러는 %d이하라 한장의 카드를 더 받았습니다.\n";
	private static final String CARD_DELIMITER = "카드: ";

	public static void noticeDrawTwoCards(Players players) {
		System.out.println();
		String playerNames = players.toList().stream()
			.map(Gamer::getName)
			.collect(Collectors.joining(", "));
		System.out.printf(NOTICE_DRAWING_CARDS, players.getDealerName(), playerNames, INITIAL_DRAWING_COUNT);
	}

	public static void noticePlayersCards(Dealer dealer, Players players) {
		System.out.println(dealer.getName() + COLON_DELIMITER + makeDealerCardNames(dealer));
		for (Player player : players.toList()) {
			noticePlayerCards(player);
		}
		System.out.println();
	}

	public static void noticePlayersPoint(Dealer dealer, Players players) {
		System.out.println();
		System.out.println(dealer.getName() + COLON_DELIMITER + makePlayerCardNames(dealer) + RESULT_DELIMITER
			+ dealer.calculatePoint());
		for (Player player : players.toList()) {
			System.out.println(player.getName() + CARD_DELIMITER + makePlayerCardNames(player)
				+ RESULT_DELIMITER + player.calculatePoint());
		}
	}

	private static String makePlayerCardNames(Gamer player) {
		return player.getPlayerState().cards().getCards().stream()
			.map(OutputView::makeCardInfo)
			.collect(Collectors.joining(COMMA_DELIMITER_TO_PRINT));
	}

	private static String makeDealerCardNames(Gamer dealer) {
		return dealer.getPlayerState().cards().getCards().stream()
			.limit(COUNT_OF_DEALER_OPENING_CARDS)
			.map(OutputView::makeCardInfo)
			.collect(Collectors.joining(COMMA_DELIMITER));
	}

	private static String makeCardInfo(Card card) {
		return card.getNumberName() + card.getPatternName();
	}

	public static void noticeDealerReceiveCard() {
		System.out.printf(NOTICE_DEALER_RECEIVE, MAX_OF_RECEIVE_MORE_CARD);
	}

	public static void noticePlayerCards(Player player) {
		System.out.println(player.getName() + CARD_DELIMITER + makePlayerCardNames(player));
	}

	public static void printDealerResult(int result) {
		System.out.println();
		System.out.println(NOTICE_FINAL_PROFIT);
		System.out.println(NAME_OF_DEALER + COLON_DELIMITER + result);
	}

	public static void printPlayersResult(Players players) {
		for (Player player : players.toList()) {
			System.out.println(player.getName() + COLON_DELIMITER + player.getMoney());
		}
	}
}
