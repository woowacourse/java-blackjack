package blackjack.domain.game;

import blackjack.controller.BlackjackCommand;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;

public class BlackjackGame {
    private final Players players;
    private final Deck deck;

    public BlackjackGame(final Players players, final Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void initialDraw() {
        players.initialDraw(deck);
    }

    public void drawByDealer() {
        players.drawByDealer(deck);
    }

    public void drawByGambler(final Player player, final BlackjackCommand command) {
        if (command.isHit()) {
            player.draw(deck);
            return;
        }
        player.stay();
    }

    public BlackjackGameResult play() {
        return new BlackjackGameResult(players.play());
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }
}
