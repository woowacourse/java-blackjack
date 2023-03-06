package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Players;

public class BlackjackGame {
    private final Players players;

    public BlackjackGame(final Players players) {
        this.players = players;
    }

    public Players getPlayers() {
        return players;
    }

    public void drawByDealer(final Deck deck) {
        players.drawByDealer(deck);
    }

    public BlackjackGameResult play() {
        return new BlackjackGameResult(players.play());
    }
}
