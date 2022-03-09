package blackjack.view;

import blackjack.domain.ParticipantsDto;
import blackjack.domain.Participant;
import blackjack.domain.Participants;

public class ResultView {

    private static final String RESULT_DELIMITER = " - 결과: ";

    public void showStartingStatus(ParticipantsDto participantsDto) {
        System.out.println(participantsDto.getDealerOpenCard());
        for (Participant player : participantsDto.getPlayers()) {
            showEachPlayerCurrentStatus(participantsDto, player);
        }
    }

    public void showCurrentStatus(ParticipantsDto participantsDto) {
        Participant dealer = participantsDto.getDealer();
        showEachPlayerCurrentStatus(participantsDto, dealer);
        for (Participant player : participantsDto.getPlayers()) {
            showEachPlayerCurrentStatus(participantsDto, player);
        }
    }

    public void showEachPlayerCurrentStatus(ParticipantsDto participantsDto, Participant participant) {
        System.out.println(getEachPlayerStatus(participantsDto, participant));
    }

    private String getEachPlayerStatus(ParticipantsDto participantsDto, Participant participant) {
        return participantsDto.getPlayerCardStatus(participant);
    }

    public void showFinishedStatus(ParticipantsDto participantsDto) {
        Participant dealer = participantsDto.getDealer();
        System.out.println(getEachPlayerStatus(participantsDto, dealer) + RESULT_DELIMITER + dealer.getScore());
        for (Participant player : participantsDto.getPlayers()) {
            System.out.println(getEachPlayerStatus(participantsDto, player) + RESULT_DELIMITER + player.getScore());
        }
    }

	public void showResult(Participants participants) {

	}
}
