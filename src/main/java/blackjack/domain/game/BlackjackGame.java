package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;

public class BlackjackGame {
    private final Players players;

    public BlackjackGame(final Players players) {
        this.players = players;
    }

    public void initialDraw(final Deck deck) {
        players.initialDraw(deck);
    }

    public void drawByDealer(final Deck deck) {
        players.drawByDealer(deck);
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
