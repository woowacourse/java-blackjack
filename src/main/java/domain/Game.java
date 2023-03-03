package domain;

import domain.user.Participant;
import java.util.List;

public class Game {

    public static final int DEALER_THRESHOLDS = 16;
    private final Deck deck = new Deck();
    private final Participants participants;

    public Game(Participants participants) {
        this.participants = participants;
    }

    public void deal(Participant participant) {
        participant.addCard(deck.draw());
    }

    public void ready() {
        deck.shuffle();
        List<Participant> allParticipants = participants.getAllParticipants();
        allParticipants.forEach((participant -> {
            deal(participant);
            deal(participant);
            participants.update(participant);
        }));
    }

    public List<Participant> getReadyResults() {
        return participants.getAllParticipants();
    }

    public void run(Participant currentParticipant, BlackJackAction action) {
        if (action == BlackJackAction.HIT) {
            deal(currentParticipant);
        }
        participants.update(currentParticipant);
    }

    public Participant getCurrentParticipant() {
        return participants.getCurrentParticipant();
    }

    public boolean isDealerNeedCard(Participant dealer) {
        return dealer.calculateScore() <= DEALER_THRESHOLDS;
    }
}
