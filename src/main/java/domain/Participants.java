package domain;

import domain.constants.Outcome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Participants {
    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<String> playerNames) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        for (String playerName : playerNames) {
            participants.add(new Player(playerName));
        }
        return new Participants(participants);
    }

    public List<Outcome> getOutcomesIf(final Function<Player, Boolean> function) {
        return participants.stream()
                .map(player -> Outcome.from(function.apply(player)))
                .toList();
    }

    public List<Player> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public Player getDealer() {
        return participants.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
