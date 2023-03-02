package domain;

import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int BLACK_JACK_NUMBER = 21;
    private static final int DEALER_THRESHOLD = 16;
    private static final int START_HIT_COUNT = 2;

    private final Deck deck;
    private final People people;

    public BlackjackGame(List<String> playerNames, String dealerName, NumberGenerator numberGenerator) {
        this.deck = new Deck(numberGenerator);
        this.people = new People(playerNames, dealerName);
    }

    public void letDealerHitUntilThreshold() {
        people.letDealerHitUntilThreshold(deck, DEALER_THRESHOLD);
    }

    public void startHit() {
        for (int i = 0; i < START_HIT_COUNT; i++) {
            people.letPlayersToHit(deck);
        }
    }

    public boolean isBurst(String playerName) {
        return people.isBurst(playerName, BLACK_JACK_NUMBER);
    }

    public Map<Player, GameResult> getGameResultForAllPlayer() {
        return people.makeGameResultForAllPlayer();
    }

    public void hitFor(String playerName) {
        people.letPlayerToHit(playerName, deck);
    }

    public People getPeople() {
        return people;
    }
}
