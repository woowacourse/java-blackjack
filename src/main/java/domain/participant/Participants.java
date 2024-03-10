package domain.participant;

import controller.dto.ParticipantOutcome;
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

    public List<ParticipantOutcome> getParticipantOutcomesIf(final Function<Participant, Boolean> function) {
        return participants.stream()
                .map(participant -> new ParticipantOutcome(
                        participant.getName(),
                        Outcome.from(function.apply(participant))
                ))
                .toList();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
