package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Participant;
import blackjack.domain.card.Card;
import blackjack.domain.dto.BlackJackDto;
import blackjack.domain.dto.CardsDto;
import blackjack.domain.dto.ParticipantDto;

public class ResultView {

	private static final String MESSAGE_HAND_OUT_CARD = "%n딜러와 %s 에게 2장의 카드를 나누었습니다.%n";
	private static final String NAME_DELIMITER = ", ";
	private static final String MESSAGE_SCORE_OVER_21 = "가진 카드의 합이 21 이상이 되어 더 이상 카드를 받을 수 없습니다.";
	private static final String MESSAGE_DEALER_NOT_RECEIVE = "딜러는 16이하라 한장의 카드를 더 받았습니다";
	private static final String MESSAGE_DEALER_RECEIVE = "딜러는 17이상이라 카드를 더 받지 않았습니다.";
	private static final String RESULT_DELIMITER = " - 결과: ";
	private static final String MESSAGE_FINAL_RESULT = "\n## 최종 승패";

	public static void showStartingStatus(BlackJackDto blackJackDto) {
		String[] playerNames = blackJackDto.getPlayers().stream()
			.map(ParticipantDto::from)
			.map(ParticipantDto::getName)
			.toArray(String[]::new);

		System.out.printf(MESSAGE_HAND_OUT_CARD, String.join(NAME_DELIMITER, playerNames));

		ParticipantDto participantDto = ParticipantDto.from(blackJackDto.getDealer());
		CardsDto cardsDto = CardsDto.from(participantDto.getCards());
		System.out.println(participantDto.getName() + ": " + cardsDto.getCards().get(0).getName());

		for (Participant player : blackJackDto.getPlayers()) {
			showEachPlayerCurrentStatus(player);
		}
		System.out.println();
	}

	public static void showEachPlayerCurrentStatus(Participant participant) {
		System.out.println(getEachPlayerStatus(ParticipantDto.from(participant)));
		if (participant.isOverMaxScore()) {
			System.out.println(MESSAGE_SCORE_OVER_21);
		}
	}

	private static String getEachPlayerStatus(ParticipantDto participantDto) {
		CardsDto cardsDto = CardsDto.from(participantDto.getCards());
		List<String> playerCardStatus = cardsDto.getCards().stream()
			.map(Card::getName)
			.collect(Collectors.toList());

		return participantDto.getName() + ": " + String.join(", ", playerCardStatus);
	}

	public static void showWhetherDealerReceivedOrNot(Boolean isReceived) {
		System.out.println();
		if (isReceived) {
			System.out.println(MESSAGE_DEALER_NOT_RECEIVE);
			return;
		}
		System.out.println(MESSAGE_DEALER_RECEIVE);
	}

	public static void showFinalStatus(BlackJackDto blackJackDto) {
		System.out.println();
		Participant dealer = blackJackDto.getDealer();
		System.out.println(getEachPlayerStatus(ParticipantDto.from(dealer)) + RESULT_DELIMITER + dealer.getScore());
		for (Participant player : blackJackDto.getPlayers()) {
			System.out.println(getEachPlayerStatus(ParticipantDto.from(player)) + RESULT_DELIMITER + player.getScore());
		}
	}

	public static void showResult(BlackJackDto blackJackDto) {
		System.out.println(MESSAGE_FINAL_RESULT);

		Map<Participant, Boolean> result = blackJackDto.getResult();

		int loseCount = (int)result.values().stream()
			.filter(value -> value)
			.count();
		int winCount = result.size() - loseCount;

		ParticipantDto participantDto = ParticipantDto.from(blackJackDto.getDealer());
		System.out.println(participantDto.getName() + ": " + winCount + "승 " + loseCount + "패 ");

		List<String> playersResults = result.keySet().stream()
			.map(ParticipantDto::from)
			.map(player -> player.getName() + ": " + decodeResult(result.get(player)))
			.collect(Collectors.toList());
		playersResults.forEach(System.out::println);
	}

	private static String decodeResult(Boolean isWin) {
		if (isWin) {
			return "승 ";
		}
		return "패 ";
	}
}
