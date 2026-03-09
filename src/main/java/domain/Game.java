package domain;

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

    public Game(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<Card> getDealerCard() {
        return dealer.getHand();
    }

    public Map<String, List<Card>> getAllPlayerCard() {
        Map<String, List<Card>> playerCards = new LinkedHashMap<>();
        for (String name : players.getAllPlayersName()) {
            playerCards.put(name, players.getPlayerCards(name));
        }

        return playerCards;
    }

    public List<Card> getPlayerCard(String name) {
        return players.getPlayerCards(name);
    }

    public void initializeGame(Deck deck) {
        players.getAllPlayersName().forEach(name ->
                players.distributeCards(name, List.of(deck.drawCard(), deck.drawCard()))
        );
        dealer.addCards(List.of(deck.drawCard(), deck.drawCard()));
    }

    public void distributeCard(String name, Deck deck) {
        players.distributeCard(name, deck.drawCard());
    }

    public void distributeCard(Deck deck) {
        dealer.addCard(deck.drawCard());
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

    public void dealerHit(Deck deck) {
        distributeCard(deck);
    }

    public Map<Result, Integer> getDealerResult() {
        int dealerScore = dealer.getScore();
        boolean dealerBurst = dealer.isBust();
        return Result.calculateDealerResult(players.decideAllResults(dealerScore, dealerBurst));
    }

    public Result getPlayerResult(String name) {
        int dealerScore = dealer.getScore();
        boolean dealerBurst = dealer.isBust();
        return players.getPlayerResult(name, dealerScore, dealerBurst);
    }

    public int getPlayerScore(String name) {
        return players.getPlayerScore(name);
    }

    public int getDealerScore() {
        return dealer.getScore();
    }
}
