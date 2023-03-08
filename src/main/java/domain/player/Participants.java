package domain.player;

import java.util.*;

import static java.util.stream.Collectors.toList;

public final class Participants {

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<Participant> participants) {
        return new Participants(participants);
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }

    public List<String> getNames() {
        return participants.stream()
                .map(Participant::getName)
                .collect(toList());
    }
}
