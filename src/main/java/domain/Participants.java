package domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        names.forEach(name -> participants.add(new Player(name)));
        return new Participants(participants);
    }

    public void deal(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.draw());
        }
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Participant participant : participants) {
            addParticipantIfPlayer(players, participant);
        }
        return players;
    }

    private static void addParticipantIfPlayer(ArrayList<Player> players, Participant participant) {
        if (participant.getClass().isInstance(Player.class)) {
            players.add((Player)participant);
        }
    }
}
