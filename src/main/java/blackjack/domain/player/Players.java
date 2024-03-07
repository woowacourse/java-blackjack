package blackjack.domain.player;

import java.util.List;

public class Players {
    private final Dealer dealer;
    private final List<GamePlayer> gamePlayers;

    public Players(Dealer dealer, List<GamePlayer> gamePlayers) {
        this.dealer = dealer;
        this.gamePlayers = gamePlayers;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
}
