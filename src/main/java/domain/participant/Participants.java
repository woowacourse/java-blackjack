package domain.participant;

import domain.card.GameCardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {
    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        this.participants = new ArrayList<>(participants);
    }

    public void drawTwoCards(GameCardDeck gameCardDeck) {
        for (Participant participant : participants) {
            participant.drawCard(gameCardDeck, 2);
        }
    }

    public Participant findDealer() {
        return participants.stream()
                .filter(Participant::areYouDealer)
                .findFirst()
                .orElseThrow();
    }

    public Participants findOnlyPlayers() {
        List<Participant> onlyPlayers = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.areYouDealer()) {
                continue;
            }
            onlyPlayers.add(participant);
        }
        return new Participants(onlyPlayers);
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
