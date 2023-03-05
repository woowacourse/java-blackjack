package domain.people;

import java.util.ArrayList;
import java.util.List;

import view.ErrorMessage;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());

        for (String name : names) {
            participants.add(new Player(name));
        }
        return new Participants(participants);
    }

    public Dealer findDealer() {
        Participant dealer = participants.stream()
            .filter(participant -> participant.equals(new Dealer()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(ErrorMessage.NO_DEALER.getMessage())
            );
        return (Dealer)dealer;
    }

    public List<Player> findPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Participant participant : participants) {
            addParticipantIfPlayer(players, participant);
        }
        return players;
    }

    private void addParticipantIfPlayer(ArrayList<Player> players, Participant participant) {
        if (!participant.equals(new Dealer())) {
            players.add((Player)participant);
        }
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
