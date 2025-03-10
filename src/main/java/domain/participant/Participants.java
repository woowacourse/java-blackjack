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

    public void drawCardTo(GameCardDeck gameCardDeck, Participant unKnownParticipant) {
        Participant participant = findParticipant(unKnownParticipant);
        participant.drawCard(gameCardDeck, 1);
    }

    private Participant findParticipant(Participant unKnownParticipant) {
        return participants.stream()
                .filter(participant -> participant.equals(unKnownParticipant))
                .findFirst()
                .orElseThrow();
    }

    public boolean ableToDraw(Participant unKnownParticipant) {
        Participant participant = findParticipant(unKnownParticipant);
        return participant.ableToDraw();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
