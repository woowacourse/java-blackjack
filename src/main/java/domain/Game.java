package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.GameResult;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.List;
import java.util.Map;

public class Game {

    private final Players players;
    private final Dealer dealer;

    public Game(List<String> playerNames, Dealer dealer) {
        this.players = new Players(playerNames);
        this.dealer = dealer;
    }

    public void initializeGame(Deck deck) {
        players.getAllPlayersName().forEach(name ->
                players.distributeCards(name, List.of(deck.drawCard(), deck.drawCard()))
        );
        dealer.addCards(List.of(deck.drawCard(), deck.drawCard()));
    }

    public boolean playPlayerTurn(Name name, Deck deck, boolean wantHit) {
        if (wantHit) {
            players.distributeCard(name, deck.drawCard());
        }
        return !players.checkScoreUnderCriterion(name);
    }

    public boolean playDealerTurn(Deck deck) {
        dealer.addCard(deck.drawCard());

        return !dealer.checkScoreUnderCriterion();
    }

    public List<Name> getAllPlayersName() {
        return players.getAllPlayersName();
    }

    public GameResult getPlayerResult(Name name) {
        int dealerScore = dealer.getScore();
        boolean dealerBurst = dealer.isBust();
        return players.getPlayerResult(name, dealerScore, dealerBurst);
    }

    public Map<GameResult, Integer> getDealerResult() {
        int dealerScore = dealer.getScore();
        boolean dealerBurst = dealer.isBust();
        return GameResult.calculateDealerResult(players.decideAllResults(dealerScore, dealerBurst));
    }

    public List<Card> getPlayerCard(Name name) {
        return players.getPlayerCards(name);
    }

    public List<Card> getDealerCard() {
        return dealer.getHand();
    }

    public int getPlayerScore(Name name) {
        return players.getPlayerScore(name);
    }

    public int getDealerScore() {
        return dealer.getScore();
    }
}
