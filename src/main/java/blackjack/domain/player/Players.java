package blackjack.domain.player;

import java.util.List;

public class Players {
    private final Dealer dealer;
    private final List<GamePlayer> gamePlayers;

    public Players(final Dealer dealer, final List<GamePlayer> gamePlayers) {
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
