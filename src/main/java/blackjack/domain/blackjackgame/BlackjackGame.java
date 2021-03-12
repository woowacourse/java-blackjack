package blackjack.domain.blackjackgame;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class BlackjackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players) {
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public void start() {
        dealer.initialDraw(deck);
        players.initialDraw(deck);
        checkIsEnd();
    }

    public Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    public boolean isNotEnd() {
        return players.isNotEnd();
    }

    public void drawCurrentPlayer(boolean isDraw) {
        Player player = getCurrentPlayer();
        if (isDraw && player.canDraw()) {
            player.draw(deck.draw());
            checkIsEnd();
            return;
        }
        player.stay();
        checkIsEnd();
    }

    private void checkIsEnd() {
        if (players.isAllFinished()) {
            calculateGameResult();
        }
    }

    private void calculateGameResult() {
        if (dealer.canDraw()) {
            dealer.draw(deck.draw());
        }
        players.calculateGameResult(dealer);
    }

}
