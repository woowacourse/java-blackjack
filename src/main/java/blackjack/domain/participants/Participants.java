package blackjack.domain.participants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants valueOf(Dealer dealer, Players players) {
        List<Participant> participants = new ArrayList<>(Collections.singletonList(dealer));
        participants.addAll(players.unwrap());
        return new Participants(participants);
    }

    public List<Participant> unwrap() {
        return new ArrayList<>(participants);
    }
}
