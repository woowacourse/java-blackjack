package domain;

import static constant.GameRule.INIT_CARD_COUNT;

import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Dealer;
import domain.participant.Players;
import dto.CardDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final Players players;
    private final Dealer dealer;

    public Game(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void initializeGame(Deck deck) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            players.distributeCardForAllPlayers(deck);
            dealer.addCard(deck.drawCard());
        }
    }

    public void distributeCard(String name, Deck deck) {
        players.distributeCard(name, deck.drawCard());
    }

    public void distributeCard(Deck deck) {
        dealer.addCard(deck.drawCard());
    }

    public void dealerHit(Deck deck) {
        distributeCard(deck);
    }

    public boolean isPlayerBust(String name) {
        return !players.checkScoreUnderCriterion(name);
    }

    public boolean isDealerBust() {
        return !dealer.checkScoreUnderCriterion();
    }

    public void playerHit(String name, Deck deck, boolean wantHit) {
        if (wantHit) {
            distributeCard(name, deck);
        }
    }

    public List<CardDto> getDealerCard() {
        return dealer.getDealerCards();
    }

    public List<String> getAllPlayerNames() {
        return players.getAllPlayerNames();
    }

    public Map<String, List<CardDto>> getAllPlayerCards() {
        Map<String, List<CardDto>> playerCards = new LinkedHashMap<>();
        for (String name : players.getAllPlayerNames()) {
            playerCards.put(name, players.getPlayerCards(name));
        }
        return playerCards;
    }

    public List<CardDto> getPlayerCards(String name) {
        return players.getPlayerCards(name);
    }

    public Map<Result, Integer> getDealerResult() {
        int dealerScore = dealer.calculateScore();
        boolean dealerBust = dealer.isBust();
        return dealer.calculateResults(players.decideAllResults(dealerScore, dealerBust));
    }

    public Result getPlayerResult(String name) {
        int dealerScore = dealer.calculateScore();
        boolean dealerBust = dealer.isBust();
        return players.getPlayerResult(name, dealerScore, dealerBust);
    }

    public int getPlayerScore(String name) {
        return players.getPlayerScore(name);
    }

    public int getDealerScore() {
        return dealer.calculateScore();
    }
}
