package domain;

import java.util.List;
import java.util.stream.Collectors;

public final class Participants {
    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> playerNames) {
        List<Participant> participants = playerNames.stream()
                .map(Participant::from)
                .collect(Collectors.toList());

        return new Participants(participants);
    }

    public void drawCard(final Deck deck) {
        participants.forEach(participant -> {
                    participant.takeCard(deck.drawCard());
                    participant.takeCard(deck.drawCard());
                }
        );
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }
}
