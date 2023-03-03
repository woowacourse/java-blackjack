package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BlackJackGame {
    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final Players players) {
        dealer = new Dealer();
        this.players = players;
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void dealerDrawCard(final Deck deck) {
        dealer.drawCard(deck.popCard());
    }

    public void calculateResult() {
        players.calculateResult(dealer);
    }


    public void distributeInitialCards(final Deck deck) {
        players.distributeInitialCards(deck);
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());
    }

    public void drawPlayerCard(final String name, final Deck deck) {
        players.draw(name, deck);
    }

    public int getPlayerScoreByName(final String playerName) {
        return players.getPlayerScoreByName(playerName);
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Optional<List<Card>> findCardsByPlayerName(final String playerName) {
        return players.findCardsByPlayerName(playerName);

    }

    public boolean isPlayerDrawable(final String name) {
        return players.isDrawable(name);
    }

    public Card findDealerInitialCard() {
        return dealer.getCards()
                .get(0);
    }

    public Map<String, List<Card>> findPlayerNameToCards() {
        return players.findPlayerNameToCards();
    }

    public int getDealerScore() {
        return dealer.currentScore();
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public Map<String, ResultType> getDealersResult() {
        return dealer.getResult();
    }
}
