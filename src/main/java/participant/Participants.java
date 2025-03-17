package participant;

import card.GameCardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final List<Participant> participants;

    public Participants(final List<Participant> originParticipants) {
        this.participants = new ArrayList<>(originParticipants);
    }

    public void drawTwoCards(GameCardDeck gameCardDeck) {
        for (Participant participant : participants) {
            participant.drawCard(gameCardDeck, 2);
        }
    }

    public Participant findDealer() {
        return participants.stream()
                .filter(Participant::isDealer)
                .findFirst()
                .orElseThrow();
    }

    public List<Participant> findPlayers() {
        return participants.stream().filter(participant -> !participant.isDealer())
                .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

}
