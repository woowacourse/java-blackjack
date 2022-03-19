package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Blackjack;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Revenue;

public class ResultView {

	private static final String MESSAGE_HAND_OUT_CARD = "%n딜러와 %s 에게 2장의 카드를 나누었습니다.%n";
	private static final String DELIMITER_COMMA = ", ";
	private static final String DELIMITER_COLON = ": ";
	private static final String MESSAGE_SCORE_OVER_21 = "가진 카드의 합이 21 초과가 되어 더 이상 카드를 받을 수 없습니다.";
	private static final String MESSAGE_DEALER_RECEIVE = "딜러는 16이하라 한장의 카드를 더 받았습니다";
	private static final String MESSAGE_DEALER_NOT_RECEIVE = "딜러는 17이상이라 카드를 더 받지 않았습니다.";
	private static final String RESULT_DELIMITER = " - 결과: ";
	private static final String MESSAGE_FINAL_RESULT = "\n## 최종 수익";

	public static void showStartingStatus(Blackjack blackJack) {
		System.out.printf(MESSAGE_HAND_OUT_CARD, String.join(DELIMITER_COMMA, getPlayerNames(blackJack)));
		showDealerStartingStatus(blackJack);
		showPlayerStartingStatus(blackJack);
		System.out.println();
	}

	private static List<String> getPlayerNames(Blackjack blackJack) {
		return blackJack.getPlayers().stream()
			.map(Player::getName)
			.collect(Collectors.toList());
	}

	private static void showDealerStartingStatus(Blackjack blackJack) {
		Dealer dealer = blackJack.getDealer();
		Card firstCard = dealer.getFirstCard();
		System.out.println(dealer.getName() + DELIMITER_COLON + firstCard.getName());
	}

	private static void showPlayerStartingStatus(Blackjack blackJack) {
		for (Player player : blackJack.getPlayers()) {
			showEachPlayerStatus(player);
		}
	}

	public static void showEachPlayerStatus(Player player) {
		System.out.println(getStatus(player));
		if (player.isBust()) {
			System.out.println(MESSAGE_SCORE_OVER_21);
		}
	}

	private static String getStatus(Participant participant) {
		List<String> playerCardStatus = participant.getCardNames();
		return participant.getName() + DELIMITER_COLON + String.join(DELIMITER_COMMA, playerCardStatus);
	}

	public static void showDealerHitOrNot(Boolean isEnough) {
		System.out.println();
		if (isEnough) {
			System.out.println(MESSAGE_DEALER_NOT_RECEIVE);
			return;
		}
		System.out.println(MESSAGE_DEALER_RECEIVE);
	}

	public static void showFinalStatus(Blackjack blackJack) {
		System.out.println();
		Dealer dealer = blackJack.getDealer();
		System.out.println(getStatus(dealer) + RESULT_DELIMITER + dealer.getScore());
		for (Player player : blackJack.getPlayers()) {
			System.out.println(getStatus(player) + RESULT_DELIMITER + player.getScore());
		}
	}

	public static void showResult(Blackjack blackJack) {
		System.out.println(MESSAGE_FINAL_RESULT);
		showDealerResult(blackJack);
		showPlayerResults(blackJack);
	}

	private static void showDealerResult(Blackjack blackJack) {
		Revenue revenue = Revenue.of(blackJack);
		Dealer dealer = blackJack.getDealer();
		System.out.println(dealer.getName() + DELIMITER_COLON + (-revenue.getTotalRevenue()));
	}

	private static void showPlayerResults(Blackjack blackjack) {
		Revenue revenue = Revenue.of(blackjack);
		List<Player> players = blackjack.getPlayers();
		players.stream()
			.map(player -> player.getName() + DELIMITER_COLON + revenue.getRevenue(player))
			.forEach(System.out::println);
	}
}
