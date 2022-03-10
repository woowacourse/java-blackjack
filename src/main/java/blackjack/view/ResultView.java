package blackjack.view;

import blackjack.domain.Participant;
import blackjack.domain.dto.BlackJackDto;

public class ResultView {

	private static final String MESSAGE_HAND_OUT_CARD = "%n딜러와 %s 에게 2장의 카드를 나누었습니다.%n";
	private static final String NAME_DELIMITER = ", ";
	private static final String MESSAGE_SCORE_OVER_21 = "가진 카드의 합이 21을 초과하여 더 이상 카드를 받을 수 없습니다.";
	private static final String MESSAGE_DEALER_NOT_RECEIVE = "딜러는 16이하라 한장의 카드를 더 받았습니다";
	private static final String MESSAGE_DEALER_RECEIVE = "딜러는 17이상이라 카드를 더 받지 않았습니다.";
	private static final String RESULT_DELIMITER = " - 결과: ";
	private static final String MESSAGE_FINAL_RESULT = "\n## 최종 승패";

	public static void showStartingStatus(BlackJackDto blackJackDto) {
		String[] playerNames = blackJackDto.getPlayers().stream()
			.map(Participant::getName)
			.toArray(String[]::new);

		System.out.printf(MESSAGE_HAND_OUT_CARD, String.join(NAME_DELIMITER, playerNames));
		System.out.println(blackJackDto.getDealerOpenCard());

		for (Participant player : blackJackDto.getPlayers()) {
			showEachPlayerCurrentStatus(blackJackDto, player);
		}
		System.out.println();
	}

	public static void showEachPlayerCurrentStatus(BlackJackDto blackJackDto, Participant participant) {
		System.out.println(getEachPlayerStatus(blackJackDto, participant));
		if (participant.isOverMaxScore()) {
			System.out.println(MESSAGE_SCORE_OVER_21);
		}
	}

	private static String getEachPlayerStatus(BlackJackDto blackJackDto, Participant participant) {
		return blackJackDto.getPlayerCardStatus(participant);
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
		System.out.println(getEachPlayerStatus(blackJackDto, dealer) + RESULT_DELIMITER + dealer.getScore());
		for (Participant player : blackJackDto.getPlayers()) {
			System.out.println(getEachPlayerStatus(blackJackDto, player) + RESULT_DELIMITER + player.getScore());
		}
	}

	public static void showResult(BlackJackDto blackJackDto) {
		System.out.println(MESSAGE_FINAL_RESULT);
		System.out.println(blackJackDto.getDealerResult());
		blackJackDto.getPlayersResult().forEach(System.out::println);
	}
}
