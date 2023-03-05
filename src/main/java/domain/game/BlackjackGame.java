package domain.game;

import domain.strategy.ShuffleStrategy;
import domain.user.People;
import domain.user.Player;

import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int START_HIT_COUNT = 2;

    private final Deck deck;
    private final People people;

    public BlackjackGame(List<String> playerNames, String dealerName, ShuffleStrategy shuffleStrategy) {
        this.deck = new Deck(shuffleStrategy);
        this.people = new People(playerNames, dealerName);
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

    public boolean isBurst(String playerName) {
        return people.isBurst(playerName);
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
