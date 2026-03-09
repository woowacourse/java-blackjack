package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.CardPicker;
import blackjack.domain.Rank;
import blackjack.domain.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCardPicker implements CardPicker {
    private final Random random;
    private final List<Card> notDrawnCards;

    public RandomCardPicker(Random random) {
        this.random = random;
        this.notDrawnCards = initializeNotDrawnCards();
    }

    @Override
    public Card drawCard() {
        int randomCardIndex = random.nextInt(notDrawnCards.size());
        return notDrawnCards.remove(randomCardIndex);
    }

    private List<Card> initializeNotDrawnCards() {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Shape shape : Shape.values()) {
                cards.add(new Card(rank, shape));
            }
        }
        return cards;
    }


}
