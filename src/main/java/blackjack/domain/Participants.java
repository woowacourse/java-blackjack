package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public record Participants(
        Players players,
        Dealer dealer
) {
    public List<Participant> all() {
        final List<Participant> participants = new ArrayList<>(List.of(dealer));
        participants.addAll(players.all());

        return participants;
    }
}
