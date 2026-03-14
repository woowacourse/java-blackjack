package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.CardPicker;
import blackjack.domain.Rank;
import blackjack.domain.Shape;

import java.util.ArrayList;
import java.util.List;

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
            addCardsByRank(rank, cards);
        }
        return cards;
    }

    private static void addCardsByRank(Rank rank, List<Card> cards) {
        for (Shape shape : Shape.values()) {
            cards.add(new Card(rank, shape));
        }
    }

    @Override
    public Card drawCard() {
        int randomCardIndex = numberGenerator.generate(notDrawnCards.size());
        return notDrawnCards.remove(randomCardIndex);
    }


}
