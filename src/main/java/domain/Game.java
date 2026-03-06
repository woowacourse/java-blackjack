package domain;

import domain.enums.Result;
import java.util.List;

public class Game {

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public Game(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<String> getAllPlayersNames() {
        return players.getAllPlayersName();
    }

    public void initializeGame() {
        players.initializeCard(deck.drawCard(), deck.drawCard());
        dealer.addCards(List.of(deck.drawCard(), deck.drawCard()));
    }

    public void distributeCard(String name) {
        players.distributeCard(name, deck.drawCard());
    }

    public void distributeCard() {
        dealer.addCard(deck.drawCard());
    }

    public void playerHit(String name, boolean isHit) {
        if (players.checkScoreUnderCriterion(name) && isHit) {
            distributeCard(name);
        }
    }

    public void dealerHit() {
        if (dealer.checkScoreUnderCriterion()) {
            distributeCard();
        }
    }

    public void settlementOfResults() {
        int dealerScore = dealer.cardBoard.calculateScore();
        boolean dealerBurst = dealer.cardBoard.isBurst();
        List<Result> playerResults = players.decideAllResults(dealerScore, dealerBurst);

        dealer.addResults(playerResults);
    }

    public Result getPlayerResult(String name) {
        int dealerScore = dealer.cardBoard.calculateScore();
        boolean dealerBurst = dealer.cardBoard.isBurst();
        return players.getPlayerResult(name, dealerScore, dealerBurst);
    }
}
