package domain.card;

import exception.ExceptionMessage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Deck {

    private final Deque<Card> cards;

    public Deck(ShuffleStrategy shuffleStrategy) {
        List<Card> initializeCards = initialize();
        List<Card> shuffledCards = shuffleStrategy.shuffle(initializeCards);
        this.cards = new ArrayDeque<>(shuffledCards);
    }

    private List<Card> initialize() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Emblem.values())
                .forEach(emblem -> createCard(emblem, cards));
        return cards;
    }

    private void createCard(Emblem emblem, List<Card> cards) {
        Arrays.stream(Grade.values())
                .forEach(grade -> cards.add(new Card(emblem, grade)));
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ExceptionMessage.DECK_EMPTY.getMessage());
        }
        return cards.poll();
    }
}
