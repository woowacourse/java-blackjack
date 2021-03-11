package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;

public class Game {

    private final Players players;
    private final Dealer dealer;

    private Game(Players players) {
        this.players = players;
        this.dealer = new Dealer();
        setUpTwoCardsAndState();
    }

    private void setUpTwoCardsAndState() {
        for (Player player : players.getPlayers()) {
            player.setUpTwoCardsAndState();
        }
        dealer.setUpTwoCardsAndState();
    }

    public static Game of(Players players) {
        return new Game(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
