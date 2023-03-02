package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int BLACK_JACK_NUMBER = 21;

    private final Deck deck;
    private final People people;

    public BlackjackGame(List<String> playerNames, String dealerName, NumberGenerator numberGenerator) {
        this.deck = new Deck(numberGenerator);
        this.people = new People(playerNames, dealerName);
    }

    public boolean isBurst(String playerName) {
        return people.isBurst(playerName, BLACK_JACK_NUMBER);
    }
}
