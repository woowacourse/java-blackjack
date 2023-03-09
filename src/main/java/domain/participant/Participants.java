package domain.participant;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants create(final Dealer dealer, final List<Player> players) {
        final List<Participant> participants = makeParticipants(dealer, players);
        return new Participants(participants);
    }

    private static List<Participant> makeParticipants(final Dealer dealer, final List<Player> players) {
        final List<Participant> participants = new ArrayList<>(players);
        participants.add(dealer);
        return participants;
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }
}
