package domain;

import domain.user.Participant;
import java.util.List;

public class Game {

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
        }));
    }
}
