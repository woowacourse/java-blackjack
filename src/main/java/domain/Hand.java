package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    void drawCard() {
        cards.add(createCard());
    }

    private Card createCard() {
        return new Card(
                CardRank.values()[new Random().nextInt(CardRank.values().length)],
                CardMark.values()[new Random().nextInt(CardMark.values().length)]
        );
    }

    List<Card> cards() {
        return List.copyOf(cards);
    }
}
