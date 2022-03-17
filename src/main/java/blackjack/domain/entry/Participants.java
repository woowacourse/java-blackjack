package blackjack.domain.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return participants;
    }

    public Map<Player, Integer> getGameResults() {
        return players.stream()
            .collect(Collectors.toMap(player -> player, player -> player.countBettingMoney(dealer)));
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return List.copyOf(players);
    }
}
