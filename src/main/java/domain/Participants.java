package domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        List<Participant> participants = new ArrayList<>();
        names.forEach(name -> participants.add(new Player(name)));
        participants.add(new Dealer());
        return new Participants(participants);
    }

    public void deal(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.draw());
        }
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }
}
