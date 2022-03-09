package blackjack.view;

import blackjack.domain.BlackJackDto;
import blackjack.domain.Participant;

import java.util.Map;

public class ResultView {

    private static final String RESULT_DELIMITER = " - 결과: ";
    private static final String NAME_DELIMITER = ": ";

    public void showStartingStatus(BlackJackDto blackJackDto) {
        System.out.println(blackJackDto.getDealerOpenCard());
        for (Participant player : blackJackDto.getPlayers()) {
            showEachPlayerCurrentStatus(blackJackDto, player);
        }
    }

    public void showCurrentStatus(BlackJackDto blackJackDto) {
        Participant dealer = blackJackDto.getDealer();
        showEachPlayerCurrentStatus(blackJackDto, dealer);
        for (Participant player : blackJackDto.getPlayers()) {
            showEachPlayerCurrentStatus(blackJackDto, player);
        }
    }

    public void showEachPlayerCurrentStatus(BlackJackDto blackJackDto, Participant participant) {
        System.out.println(getEachPlayerStatus(blackJackDto, participant));
    }

    private String getEachPlayerStatus(BlackJackDto blackJackDto, Participant participant) {
        return blackJackDto.getPlayerCardStatus(participant);
    }

    public void showFinishedStatus(BlackJackDto blackJackDto) {
        Participant dealer = blackJackDto.getDealer();
        System.out.println(getEachPlayerStatus(blackJackDto, dealer) + RESULT_DELIMITER + dealer.getScore());
        for (Participant player : blackJackDto.getPlayers()) {
            System.out.println(getEachPlayerStatus(blackJackDto, player) + RESULT_DELIMITER + player.getScore());
        }
    }

    public void showResult(BlackJackDto blackJackDto) {
        System.out.println("## 최종 승패");
        System.out.println(blackJackDto.getDealerResult());
        blackJackDto.getPlayersResult().forEach(System.out::println);
    }
}
