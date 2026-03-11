package domain;

import static domain.constant.GameRule.INIT_CARD_COUNT;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Dealer;
import domain.participant.Players;
import java.util.List;
import java.util.Map;

public class Game {

    private final Players players;
    private final Dealer dealer;

    public Game(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public void initializeGame(Deck deck) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            players.distributeCardForAllPlayers(deck);
            dealer.addCard(deck.drawCard());
        }
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

    public void playerHit(String name, Deck deck, boolean wantHit) {
        if (!wantHit) {
            return;
        }
        players.distributeCard(name, deck.drawCard());
    }

    public void dealerHit(Deck deck) {
        dealer.addCard(deck.drawCard());
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

    public Result getPlayerResult(String name) {
        int dealerScore = dealer.calculateScore();
        boolean dealerBust = dealer.isBust();
        return players.getPlayerResult(name, dealerScore, dealerBust);
    }

    public Map<Result, Integer> getDealerResult() {
        int dealerScore = dealer.calculateScore();
        boolean dealerBust = dealer.isBust();
        return dealer.calculateResults(players.decideAllResults(dealerScore, dealerBust));
    }

    public int getPlayerScore(String name) {
        return players.getPlayerScore(name);
    }

    public int getDealerScore() {
        return dealer.calculateScore();
    }
}
