package blackjack;

import blackjack.domain.Participants;
import blackjack.view.InputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<String> participantNames = InputView.inputParticipantNames();
        Participants participants = Participants.from(participantNames);


    }
}
