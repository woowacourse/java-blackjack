package blackjack.domain;

import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class BlackjackManager {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackManager(final Dealer dealer, final Players players) {
        this(CardDeck.newShuffledDeck(), dealer, players);
    }

    public BlackjackManager(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public void initDrawCards() {
        this.dealer.initDraw(this.cardDeck);
        this.players.initDraw(this.cardDeck);
    }

    public void hitOrStayCurrentPlayer(boolean isPlayerHit) {
        if (isPlayerHit) {
            this.players.drawCurrentTurnPlayer(this.cardDeck.draw());
            return;
        }
        this.players.stayCurrentTurnPlayer();
    }

    public boolean isFinishedCurrentPlayer() {
        return this.players.isPlayerDone();
    }

    public boolean isFinishedAllPlayers() {
        return this.players.isAllPlayerDone();
    }

    public void hitDealer() {
        this.dealer.draw(this.cardDeck.draw());
    }

    public void stayDealer() {
        this.dealer.stay();
    }

    public boolean isDealerScoreOverThenLimit() {
        return this.dealer.isOverThenLimitScore();
    }

    public boolean isFinishedDealer() {
        return this.dealer.isFinished();
    }

    public Players getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {
        return this.players.getCurrentTurnPlayer();
    }

    public String getCurrentPlayerName() {
        return this.players.getCurrentPlayerName();
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public boolean isBetAllPlayers() {
        return this.players.isAllPlayerDone();
    }

    public String getCurrentBetPlayerName() {
        return this.players.getCurrentPlayerName();
    }

    public void betCurrentPlayer(final double betAmount) {
        this.players.betCurrentPlayer(betAmount);
    }

    public void passTurnToNextPlayer() {
        this.players.passTurnToNextPlayer();
    }

    public void readyToPlay() {
        this.players.resetIndex();
    }
}
