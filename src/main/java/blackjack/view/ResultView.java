package blackjack.view;

import blackjack.domain.Blackjack;
import blackjack.domain.BlackjackDto;
import blackjack.domain.Participant;

public class ResultView {

	private static final String RESULT_DELIMITER = " - 결과: ";

	public void showStartingStatus(BlackjackDto blackjackDto) {
        System.out.println(blackjackDto.getDealerOpenCard());
        for (Participant player : blackjackDto.getPlayers()) {
            System.out.println(blackjackDto.getPlayerCardStatus(player));
        }
    }

    public void showCurrentStatus(BlackjackDto blackjackDto) {
        Participant dealer = blackjackDto.getDealer();
        System.out.println(blackjackDto.getPlayerCardStatus(dealer));
        for (Participant player : blackjackDto.getPlayers()) {
            System.out.println(blackjackDto.getPlayerCardStatus(player));
        }
    }

	public void showFinishedStatus(BlackjackDto blackjackDto) {
		Participant dealer = blackjackDto.getDealer();
		System.out.println(blackjackDto.getPlayerCardStatus(dealer) + RESULT_DELIMITER + dealer.getScore());
		for (Participant player : blackjackDto.getPlayers()) {
			System.out.println(blackjackDto.getPlayerCardStatus(player) + RESULT_DELIMITER + player.getScore());
		}
	}
}
