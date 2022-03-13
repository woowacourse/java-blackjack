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
	private static final String DELIMITER_COMMA = ", ";
	private static final String DELIMITER_COLON = ": ";
	private static final String MESSAGE_SCORE_OVER_21 = "가진 카드의 합이 21 초과가 되어 더 이상 카드를 받을 수 없습니다.";
	private static final String MESSAGE_DEALER_RECEIVE = "딜러는 16이하라 한장의 카드를 더 받았습니다";
	private static final String MESSAGE_DEALER_NOT_RECEIVE = "딜러는 17이상이라 카드를 더 받지 않았습니다.";
	private static final String RESULT_DELIMITER = " - 결과: ";
	private static final String MESSAGE_FINAL_RESULT = "\n## 최종 승패";
	private static final String MESSAGE_WIN = "승 ";
	private static final String MESSAGE_LOSE = "패 ";

	public static void showStartingStatus(BlackJackDto blackJackDto) {
		System.out.printf(MESSAGE_HAND_OUT_CARD, String.join(DELIMITER_COMMA, getPlayerNames(blackJackDto)));
		showDealerStartingStatus(blackJackDto);
		showPlayerStartingStatus(blackJackDto);
		System.out.println();
	}

	private static String[] getPlayerNames(BlackJackDto blackJackDto) {
		return blackJackDto.getPlayers().stream()
			.map(ParticipantDto::from)
			.map(ParticipantDto::getName)
			.toArray(String[]::new);
	}

	private static void showDealerStartingStatus(BlackJackDto blackJackDto) {
		ParticipantDto participantDto = ParticipantDto.from(blackJackDto.getDealer());
		CardsDto cardsDto = CardsDto.from(participantDto.getCards());
		System.out.println(participantDto.getName() + DELIMITER_COLON + cardsDto.getCards().get(0).getName());
	}

	private static void showPlayerStartingStatus(BlackJackDto blackJackDto) {
		for (Participant player : blackJackDto.getPlayers()) {
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
		CardsDto cardsDto = CardsDto.from(participantDto.getCards());
		List<String> playerCardStatus = cardsDto.getCards().stream()
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

	public static void showFinalStatus(BlackJackDto blackJackDto) {
		System.out.println();
		Participant dealer = blackJackDto.getDealer();
		System.out.println(getStatus(ParticipantDto.from(dealer)) + RESULT_DELIMITER + dealer.getScore());
		for (Participant player : blackJackDto.getPlayers()) {
			System.out.println(getStatus(ParticipantDto.from(player)) + RESULT_DELIMITER + player.getScore());
		}
	}

	public static void showResult(BlackJackDto blackJackDto) {
		System.out.println(MESSAGE_FINAL_RESULT);
		Map<Participant, Boolean> result = blackJackDto.getResult();
		showDealerResult(blackJackDto, result);
		showPlayerResults(result);
	}

	private static void showDealerResult(BlackJackDto blackJackDto, Map<Participant, Boolean> result) {
		int loseCount = (int)result.values().stream()
			.filter(value -> value)
			.count();
		int winCount = result.size() - loseCount;

		ParticipantDto dealer = ParticipantDto.from(blackJackDto.getDealer());
		System.out.println(dealer.getName() + DELIMITER_COLON + winCount + MESSAGE_WIN + loseCount + MESSAGE_LOSE);
	}

	private static void showPlayerResults(Map<Participant, Boolean> result) {
		result.keySet().stream()
			.map(participant -> ParticipantDto.from(participant).getName() + DELIMITER_COLON + decodeResult(result.get(participant)))
			.forEach(System.out::println);
	}

	private static String decodeResult(Boolean isWin) {
		if (isWin) {
			return MESSAGE_WIN;
		}
		return MESSAGE_LOSE;
	}
}
