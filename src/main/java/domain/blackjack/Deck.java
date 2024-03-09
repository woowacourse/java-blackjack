package domain.blackjack;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        init();
    }

    private void init() {
        for (Shape shape : Shape.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> cards.add(new Card(shape, rank)));
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 더 이상 카드가 없습니다.");
        }
        return cards.remove(0);
    }

    public int getCardCount() {
        return cards.size();
    }
}
