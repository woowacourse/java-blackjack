package blackjack.domain.blackjackgame;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.LinkedList;
import java.util.Queue;

public class BlackjackGame {

    private static final String DEALER_NAME = "딜러";
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;
    private final Queue<Player> currentPlayers;
    private boolean isNotEnd;

    public BlackjackGame(Players players) {
        this.deck = new Deck();
        this.dealer = new Dealer(DEALER_NAME);
        this.players = players;
        this.isNotEnd = true;
        this.currentPlayers = new LinkedList<>(players.getPlayers());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public boolean isNotEnd() {
        return isNotEnd;
    }

    public Player getCurrentPlayer() {
        return currentPlayers.peek();
    }

    public void start() {
        dealer.initialDraw(deck);
        players.initialDraw(deck);
        checkIsEnd();
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
        currentPlayers.removeIf(Participant::isFinished);
        if (currentPlayers.size() == 0) {
            calculateGameResult();
            isNotEnd = false;
        }
    }

    private void calculateGameResult() {
        if (dealer.canDraw()) {
            dealer.draw(deck.draw());
        }
        players.calculateGameResult(dealer);
    }

}
