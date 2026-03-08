package team.blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = Arrays.stream(Card.values())
                .collect(Collectors.toList());
    }

    public Card draw() {
        shuffle();
        try {
            return cards.getFirst();
        } finally {
            cards.removeFirst();
        }
    }

    private void shuffle(){
        Collections.shuffle(this.cards);
    }
}
