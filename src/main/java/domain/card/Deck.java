package domain.card;

import domain.participant.Hand;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {

    private static final int INIT_CARD_SIZE = 2;

    private final Deque<Card> cards;

    public Deck() {
        List<Card> cards = initialize();
        Collections.shuffle(cards);
        this.cards = new ArrayDeque<>(cards);
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

    public Hand dealInitialCards() {
        List<Card> hand = new ArrayList<>();
        for (int size = 0; size < INIT_CARD_SIZE; size++) {
            Card card = drawCard();
            hand.add(card);
        }
        return new Hand(hand);
    }

    public Card drawCard() {
        return cards.poll();
    }
}
