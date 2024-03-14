package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

public class Game {
    private final Dealer dealer;
    private final Players players;

    public Game(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public GameResult makeGameResult() {
        return GameResult.of(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
