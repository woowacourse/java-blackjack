package model.participant;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final Dealer dealer;
    private final List<Player> players;

    private Participants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = List.copyOf(players);
    }

    public static Participants of(Dealer dealer, List<Player> players) {
        return new Participants(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Participant> asList() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        return List.copyOf(participants);
    }

    @Override
    public String toString() {
        return "Participants{" +
                "dealer=" + dealer +
                ", players=" + players +
                '}';
    }
}
