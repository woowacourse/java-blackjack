package blackjack.view;

import blackjack.domain.BlackjackDto;
import blackjack.domain.Participant;

public class ResultView {
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
}
