package blackjack.domain;

import java.util.List;

public class BlackjackGame {
    private final Players players;

    public BlackjackGame(final Players players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public void initialDraw(final Deck deck) {
        players.initialDraw(deck);
    }
}
