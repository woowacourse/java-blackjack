package team.blackjack.registry;

import java.util.List;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Player;

public class ParticipantRegistry {
    private final Dealer dealer;
    private List<Player> players;

    public ParticipantRegistry() {
        this.dealer = new Dealer();
    }

    public void savePlayers(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return List.copyOf(this.players);
    }

    public List<String> getPlayerNames() {
        return this.players.stream()
                .map(Player::getName)
                .toList();
    }
}
