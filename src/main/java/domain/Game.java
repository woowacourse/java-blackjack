package domain;

import static domain.constant.GameRule.INIT_CARD_COUNT;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Dealer;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final Players players;
    private final Dealer dealer;

    public Game(List<String> names, Deck deck) {
        this.players = new Players(names);
        this.dealer = new Dealer();
        initializeGame(deck);
    }

    private void initializeGame(Deck deck) {
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

    public List<Card> getPlayerCards(String name) {
        return players.getPlayerCards(name);
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public List<String> getAllPlayerNames() {
        return players.getAllPlayerNames();
    }

    public Map<String, Result> calculateAllPlayerResults() {
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (String name : players.getAllPlayerNames()) {
            playerResults.put(name, players.getPlayerResult(name, dealer));
        }
        return playerResults;
    }

    public int calculatePlayerScore(String name) {
        return players.calculatePlayerScore(name);
    }

    public int calculateDealerScore() {
        return dealer.calculateScore();
    }
}
