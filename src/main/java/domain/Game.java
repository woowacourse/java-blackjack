package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.GameResult;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    public Game(List<String> playerNames, Dealer dealer) {
        this.players = new Players(playerNames);
        this.dealer = dealer;
    }

    public void initializeGame(Deck deck) {
        players.getAllPlayersName().forEach(name ->
                players.distributeCards(name, initCards(deck))
        );
        dealer.addCards(initCards(deck));
    }

    private List<Card> initCards(Deck deck) {
        List<Card> initialCards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            initialCards.add(deck.drawCard());
        }
        return initialCards;
    }

    public boolean playPlayerTurn(Name name, Deck deck, boolean wantHit) {
        if (wantHit) {
            players.distributeCard(name, deck.drawCard());
        }
        return !players.checkScoreUnderCriterion(name);
    }

    public boolean playDealerTurn(Deck deck) {
        if (!dealer.checkScoreUnderCriterion()) {
            return false;
        }
        dealer.addCard(deck.drawCard());
        return true;
    }

    public List<Name> getAllPlayersName() {
        return players.getAllPlayersName();
    }

    public Map<Name, GameResult> getAllPlayersResult() {
        Map<Name, GameResult> playerResults = new LinkedHashMap<>();
        players.getAllPlayersName().forEach(
                name -> playerResults.put(name, players.getPlayerResult(name, dealer))
        );
        return playerResults;
    }

    public Map<GameResult, Integer> getDealerResult() {
        return GameResult.calculateDealerResult(players.decidePlayerResults(dealer).values()
                .stream()
                .toList());
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
