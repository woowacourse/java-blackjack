package blackjack.domain;

public class BlackjackGame {
    private final Players players;

    public BlackjackGame(final Players players) {
        this.players = players;
    }

    public Players getPlayers() {
        return players;
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
}
