package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants createParticipantsByNames(List<String> names) {
        List<Participant> participants = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            participants.add(player);
        }
        participants.add(new Dealer());
        return new Participants(participants);
    }

    public int size() {
        return participants.size();
    }
}
