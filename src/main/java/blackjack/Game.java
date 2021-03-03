package blackjack;

import blackjack.participant.Dealer;
import blackjack.participant.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final List<Player> players;
    private final Dealer dealer;

    public Game(List<Player> players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public static Game of(List<String> playerNames) {
        return new Game(playerNames.stream()
                                   .map(Player::new)
                                   .collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
