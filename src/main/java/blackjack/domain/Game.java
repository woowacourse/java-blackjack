package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

public class Game {
    private final Players players;
    private final Dealer dealer;

    private Game(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public static Game of(Players players) {
        return new Game(players);
    }
}
