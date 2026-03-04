package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {

    private Deque<Card> cards;

    public Deck() {
        List<Card> cards = initialize();
        Collections.shuffle(cards);
        this.cards = new ArrayDeque<>(cards);
    }

    private List<Card> initialize() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Emblem.values()).forEach(this::createCard);
        return cards;
    }

    private void createCard(Emblem emblem) {
        Arrays.stream(Grade.values())
                .forEach(grade -> cards.add(new Card(emblem, grade)));
    }


}
