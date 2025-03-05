package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardGame {

    private Deck deck;
    private List<Participant> participants;

    public List<Card> initialize() {
        List<Card> deck = Arrays.stream(TrumpNumber.values())
                .flatMap(number -> Arrays.stream(TrumpEmblem.values())
                .map(emblem -> new Card(number, emblem)))
                .collect(Collectors.toList());
        shuffle(deck);
        return new ArrayList<>(deck);
    }

    private void shuffle(List<Card> deck) {
        Collections.shuffle(deck);
    }

    public Card drawOneCard(List<Card> deck) {
        return deck.removeLast();
    }

}
