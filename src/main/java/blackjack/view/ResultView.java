package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.BlackJack;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

public class ResultView {

	private static final String MESSAGE_HAND_OUT_CARD = "%n딜러와 %s 에게 2장의 카드를 나누었습니다.%n";
	private static final String DELIMITER_COMMA = ", ";
	private static final String DELIMITER_COLON = ": ";
	private static final String MESSAGE_SCORE_OVER_21 = "가진 카드의 합이 21 초과가 되어 더 이상 카드를 받을 수 없습니다.";
	private static final String MESSAGE_DEALER_RECEIVE = "딜러는 16이하라 한장의 카드를 더 받았습니다";
	private static final String MESSAGE_DEALER_NOT_RECEIVE = "딜러는 17이상이라 카드를 더 받지 않았습니다.";
	private static final String RESULT_DELIMITER = " - 결과: ";
	private static final String MESSAGE_FINAL_RESULT = "\n## 최종 승패";
	private static final String MESSAGE_WIN = "승 ";
	private static final String MESSAGE_LOSE = "패 ";

	public static void showStartingStatus(BlackJack blackJack) {
		System.out.printf(MESSAGE_HAND_OUT_CARD, String.join(DELIMITER_COMMA, getPlayerNames(blackJack)));
		showDealerStartingStatus(blackJack);
		showPlayerStartingStatus(blackJack);
		System.out.println();
	}

	private static List<String> getPlayerNames(BlackJack blackJack) {
		return blackJack.getPlayers().stream()
			.map(Player::getName)
			.collect(Collectors.toList());
	}

	private static void showDealerStartingStatus(BlackJack blackJack) {
		Dealer dealer = blackJack.getDealer();
		Card firstCard = dealer.getFirstCard();
		System.out.println(dealer.getName() + DELIMITER_COLON + firstCard.getName());
	}

	private static void showPlayerStartingStatus(BlackJack blackJack) {
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

	public static void showFinalStatus(BlackJack blackJack) {
		System.out.println();
		Dealer dealer = blackJack.getDealer();
		System.out.println(getStatus(dealer) + RESULT_DELIMITER + dealer.getScore());
		for (Player player : blackJack.getPlayers()) {
			System.out.println(getStatus(player) + RESULT_DELIMITER + player.getScore());
		}
	}

	public static void showResult(BlackJack blackJack) {
		System.out.println(MESSAGE_FINAL_RESULT);
		Map<Player, Result> result = blackJack.calculateResult();
		showDealerResult(blackJack, result);
		showPlayerResults(result);
	}

	private static void showDealerResult(BlackJack blackJack, Map<Player, Result> result) {
		int loseCount = (int)result.values().stream()
			.filter(value -> value.equals(Result.WIN))
			.count();
		int winCount = result.size() - loseCount;

		Dealer dealer = blackJack.getDealer();
		System.out.println(dealer.getName() + DELIMITER_COLON + winCount + MESSAGE_WIN + loseCount + MESSAGE_LOSE);
	}

	private static void showPlayerResults(Map<Player, Result> result) {
		result.keySet().stream()
			.map(player -> player.getName() + DELIMITER_COLON + result.get(player).getValue())
			.forEach(System.out::println);
	}
}
