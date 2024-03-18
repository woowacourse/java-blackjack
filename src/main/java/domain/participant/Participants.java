package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(List<Player> players) {
        this.dealer = new Dealer();
        this.players = new ArrayList<>(players);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
