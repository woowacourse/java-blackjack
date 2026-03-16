package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.Result;
import java.util.List;

public class Participants {
    private final Players players;
    private final Dealer dealer;

    public Participants(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public void distributeCardForAllParticipants(Deck deck) {
        players.distributeCardForAllPlayers(deck);
        dealer.addCard(deck.drawCard());
    }

    public boolean isPlayerEnd(String name, boolean wantHit) {
        if (!wantHit) {
            return true;
        }
        return !players.checkScoreUnderCriterion(name);
    }

    public boolean isDealerEnd() {
        return !dealer.checkScoreUnderCriterion();
    }

    public void playerHit(String name, Deck deck, boolean isPlayerEnd) {
        if (isPlayerEnd) {
            return;
        }
        players.distributeCard(name, deck.drawCard());
    }

    public void dealerHit(Deck deck) {
        if (isDealerEnd()) {
            return;
        }
        dealer.addCard(deck.drawCard());
    }

    public Result calculatePlayerResult(String name) {
        return players.calculatePlayerResult(name, dealer);
    }

    public int calculatePlayerScore(String name) {
        return players.calculatePlayerScore(name);
    }

    public int calculateDealerScore() {
        return dealer.calculateScore();
    }

    public List<Card> getPlayerCards(String name) {
        return players.getPlayerCards(name);
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public List<String> getAllPlayerNames() {
        return players.getAllPlayerNames();
    }
}
