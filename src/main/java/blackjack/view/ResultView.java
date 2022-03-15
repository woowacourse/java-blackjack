package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.ParticipantDto;
import blackjack.domain.BlackJack;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

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

	private static String[] getPlayerNames(BlackJack blackJack) {
		return blackJack.getPlayers().stream()
			.map(ParticipantDto::from)
			.map(ParticipantDto::getName)
			.toArray(String[]::new);
	}

	private static void showDealerStartingStatus(BlackJack blackJack) {
		ParticipantDto participantDto = ParticipantDto.from(blackJack.getDealer());
		Card firstCard = participantDto.getCards().get(0);
		System.out.println(participantDto.getName() + DELIMITER_COLON + firstCard.getName());
	}

	private static void showPlayerStartingStatus(BlackJack blackJack) {
		for (Participant player : blackJack.getPlayers()) {
			showEachPlayerStatus(player);
		}
	}

	public static void showEachPlayerStatus(Participant player) {
		System.out.println(getStatus(ParticipantDto.from(player)));
		if (player.isOverMaxScore()) {
			System.out.println(MESSAGE_SCORE_OVER_21);
		}
	}

	private static String getStatus(ParticipantDto participantDto) {
		List<String> playerCardStatus = participantDto.getCards().stream()
			.map(Card::getName)
			.collect(Collectors.toList());

		return participantDto.getName() + DELIMITER_COLON + String.join(DELIMITER_COMMA, playerCardStatus);
	}

	public static void showWhetherDealerReceivedOrNot(Boolean isEnough) {
		System.out.println();
		if (isEnough) {
			System.out.println(MESSAGE_DEALER_NOT_RECEIVE);
			return;
		}
		System.out.println(MESSAGE_DEALER_RECEIVE);
	}

	public static void showFinalStatus(BlackJack blackJack) {
		System.out.println();
		Participant dealer = blackJack.getDealer();
		System.out.println(getStatus(ParticipantDto.from(dealer)) + RESULT_DELIMITER + dealer.getScore());
		for (Participant player : blackJack.getPlayers()) {
			System.out.println(getStatus(ParticipantDto.from(player)) + RESULT_DELIMITER + player.getScore());
		}
	}

	public static void showResult(BlackJack blackJack) {
		System.out.println(MESSAGE_FINAL_RESULT);
		Map<Participant, Boolean> result = blackJack.calculateResult();
		showDealerResult(blackJack, result);
		showPlayerResults(result);
	}

	private static void showDealerResult(BlackJack blackJack, Map<Participant, Boolean> result) {
		int loseCount = (int)result.values().stream()
			.filter(value -> value)
			.count();
		int winCount = result.size() - loseCount;

		ParticipantDto dealer = ParticipantDto.from(blackJack.getDealer());
		System.out.println(dealer.getName() + DELIMITER_COLON + winCount + MESSAGE_WIN + loseCount + MESSAGE_LOSE);
	}

	private static void showPlayerResults(Map<Participant, Boolean> result) {
		result.keySet().stream()
			.map(participant -> ParticipantDto.from(participant).getName() + DELIMITER_COLON + decodeResult(
				result.get(participant)))
			.forEach(System.out::println);
	}

	private static String decodeResult(Boolean isWin) {
		if (isWin) {
			return MESSAGE_WIN;
		}
		return MESSAGE_LOSE;
	}
}
