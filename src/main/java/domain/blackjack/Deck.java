package domain.blackjack;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final List<Card> CACHE;

    static {
        List<Card> initialCards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> initialCards.add(new Card(shape, rank)));
        }
        CACHE = initialCards;
    }

    private final List<Card> cards;

    public Deck() {
        cards = init();
    }

    private List<Card> init() {
        List<Card> initialCards = new ArrayList<>(CACHE);
        Collections.shuffle(initialCards);
        return initialCards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("드로우할 카드가 남아있지 않습니다.");
        }
        return cards.remove(0);
    }

    public List<Card> draw(int drawCount) {
        List<Card> drawCards = new ArrayList<>();
        for (int i = 0; i < drawCount; i++) {
            drawCards.add(draw());
        }
        return drawCards;
    }

    public int getCardCount() {
        return cards.size();
    }
}
