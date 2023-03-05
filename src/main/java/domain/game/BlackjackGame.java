package domain.game;

import domain.strategy.NumberGenerator;
import domain.user.People;
import domain.user.Player;

import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int START_HIT_COUNT = 2;
    private static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private final People people;

    public BlackjackGame(List<String> playerNames, NumberGenerator numberGenerator) {
        this.deck = new Deck(numberGenerator);
        this.people = new People(playerNames, DEALER_NAME);
    }

    public void letDealerHitUntilThreshold() {
        people.letDealerHitUntilThreshold(deck);
    }

    public boolean dealerNeedsHit() {
        return people.dealerNeedsHit();
    }

    public void startHit() {
        for (int i = 0; i < START_HIT_COUNT; i++) {
            people.letPlayersToHit(deck);
        }
    }

    public boolean isBust(String playerName) {
        return people.isBust(playerName);
    }

    public Map<Player, GameResult> getGameResultForAllPlayer() {
        return people.makeGameResultForAllPlayer();
    }

    public Map<GameResult, Integer> getDealerRecord() {
        return people.getDealerRecord(people.makeGameResultForAllPlayer());
    }

    public void hitFor(String playerName) {
        people.letPlayerToHit(playerName, deck);
    }

    public People getPeople() {
        return people;
    }
}
