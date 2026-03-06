package domain;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<Participant> participants;


    public Participants() {
        this.participants = new ArrayList<>();
    }

    public Participants(List<String> playerNames, Deck deck) {
        this.participants = new ArrayList<>();
        playerNames.forEach(playerName -> addParticipant(deck, playerName));
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
