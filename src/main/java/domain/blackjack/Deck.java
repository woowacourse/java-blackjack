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
        cards = init();
    }

    private List<Card> init() {
        List<Card> initialCards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> initialCards.add(new Card(shape, rank)));
        }
        Collections.shuffle(initialCards);
        return initialCards;
    }

    public Card draw() {
         return cards.remove(0);
    }

    public int getCardCount() {
        return cards.size();
    }
}
