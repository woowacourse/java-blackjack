package blackjack.domain;

import java.util.List;
import java.util.Map;

class Participants {

    private final Players players;
    private final Dealer dealer;

    Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    void distributeInitialCards(final Deck deck) {
        players.distributeInitialCards(deck);
        dealer.drawInitialCard(deck.removeCard(), deck.removeCard());
    }

    Player findPlayerByName(final String playerName) {
        return players.findPlayerByName(playerName);
    }

    boolean isPlayerDrawable(final String playerName) {
        return players.isDrawable(playerName);
    }

    void drawPlayerCard(final String playerName, final Card card) {
        players.draw(playerName, card);
    }


    boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    void drawDealerCard(final Card card) {
        dealer.drawCard(card);
    }

    Dealer getDealer() {
        return dealer;
    }

    Players getPlayers() {
        return players;
    }

    Map<String, ResultType> calculateFinalResult() {
        final BlackJackReferee blackJackReferee = BlackJackReferee.from(dealer.currentScore());
        players.calculateResult(blackJackReferee);
        return blackJackReferee.getResult();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }
}
