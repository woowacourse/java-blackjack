package blackjack.domain;

import blackjack.domain.participants.Names;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import java.util.List;

public class BlackjackGame {

    private final Participants participants;

    public BlackjackGame(final Names names, final List<Double> moneys) {
        participants = new Participants(names, moneys);
    }

    public BlackjackGame(final Participant dealer, final List<Participant> players) {
        participants = new Participants(dealer, players);
    }

    public void distributeCard() {
        participants.distributeCard();
    }

    public ParticipantResults makeParticipantResults() {
        return new ParticipantResults(participants.getDealer(), participants.getPlayers());
    }

    public Participants getParticipants() {
        return participants;
    }

}
