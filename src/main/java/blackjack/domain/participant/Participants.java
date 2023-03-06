package blackjack.domain.participant;

import blackjack.domain.BlackJackReferee;
import blackjack.domain.Deck;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import java.util.List;
import java.util.Map;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void distributeInitialCards(final Deck deck) {
        players.distributeInitialCards(deck);
        dealer.drawInitialCard(deck.removeCard(), deck.removeCard());
    }

    public Player findPlayerByName(final String playerName) {
        return players.findPlayerByName(playerName);
    }

    public boolean isPlayerDrawable(final String playerName) {
        return players.isDrawable(playerName);
    }

    public void drawPlayerCard(final String playerName, final Card card) {
        players.draw(playerName, card);
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void drawDealerCard(final Card card) {
        dealer.drawCard(card);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public Map<String, ResultType> calculateFinalResult() {
        final BlackJackReferee blackJackReferee = BlackJackReferee.from(dealer.currentScore());
        players.calculateResult(blackJackReferee);
        return blackJackReferee.getResult();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }
}
