package blackjack.view;

import blackjack.domain.BlackjackDto;
import blackjack.domain.Participant;

public class ResultView {

    private static final String RESULT_DELIMITER = " - 결과: ";

    public void showStartingStatus(BlackjackDto blackjackDto) {
        System.out.println(blackjackDto.getDealerOpenCard());
        for (Participant player : blackjackDto.getPlayers()) {
            showEachPlayerCurrentStatus(blackjackDto, player);
        }
    }

    public void showCurrentStatus(BlackjackDto blackjackDto) {
        Participant dealer = blackjackDto.getDealer();
        showEachPlayerCurrentStatus(blackjackDto, dealer);
        for (Participant player : blackjackDto.getPlayers()) {
            showEachPlayerCurrentStatus(blackjackDto, player);
        }
    }

    public void showEachPlayerCurrentStatus(BlackjackDto blackjackDto, Participant participant) {
        System.out.println(getEachPlayerStatus(blackjackDto, participant));
    }

    private String getEachPlayerStatus(BlackjackDto blackjackDto, Participant participant) {
        return blackjackDto.getPlayerCardStatus(participant);
    }

    public void showFinishedStatus(BlackjackDto blackjackDto) {
        Participant dealer = blackjackDto.getDealer();
        System.out.println(getEachPlayerStatus(blackjackDto, dealer) + RESULT_DELIMITER + dealer.getScore());
        for (Participant player : blackjackDto.getPlayers()) {
            System.out.println(getEachPlayerStatus(blackjackDto, player) + RESULT_DELIMITER + player.getScore());
        }
    }
}
