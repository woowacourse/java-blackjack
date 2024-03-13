package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {

    private final List<Player> players;
    private final Dealer dealer;

    public Participants(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Participants createWithDealer(List<Player> players) {
        return new Participants(new ArrayList<>(players), new Dealer());
    }

    public boolean doesNotContain(Participant participant) {
        return !getParticipants().contains(participant);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>(players);
        participants.add(0, dealer);

        return participants;
    }
}
