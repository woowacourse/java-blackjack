package domain;

import domain.card.Deck;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Participant;
import exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants() {
        this.participants = new ArrayList<>();
    }

    public Participants(List<String> playerNames, Deck deck) {
        this.participants = new ArrayList<>();
        validateUnique(playerNames);
        playerNames.forEach(playerName -> addParticipant(deck, playerName));
    }

    public void validateUnique(List<String> values) {
        if (isDuplicated(values)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_PARTICIPANT_NAME.getMessage());
        }
    }

    private boolean isDuplicated(List<String> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    private void addParticipant(Deck deck, String playerName) {
        Participant participant = new Participant(new Name(playerName), new Hand());
        participants.add(participant);
        participant.initHand(deck);
    }

    public void add(Participant participant) {
        participants.add(participant);
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<String> getParticipantNames() {
        return participants.stream()
                .map(Participant::getName)
                .toList();
    }
}
