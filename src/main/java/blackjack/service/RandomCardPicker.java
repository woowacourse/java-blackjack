package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.CardPicker;
import blackjack.domain.Rank;
import blackjack.domain.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCardPicker implements CardPicker {
    private final NumberGenerator numberGenerator;
    private final List<Card> notDrawnCards;

    public RandomCardPicker(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.notDrawnCards = initializeNotDrawnCards();
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

    @Override
    public Card drawCard() {
        int randomCardIndex = numberGenerator.generate(notDrawnCards.size());
        return notDrawnCards.remove(randomCardIndex);
    }


}
