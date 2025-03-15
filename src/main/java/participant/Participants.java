package participant;

import card.GameCardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Participants findPlayers() {
        List<Participant> players = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.isDealer()) {
                continue;
            }
            players.add(participant);
        }
        return new Participants(players);
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
