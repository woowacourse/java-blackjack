package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.Rank;
import blackjack.domain.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCardPicker {
    private final Random random;
    private final List<Card> notDrawnCards;

    public RandomCardPicker(Random random) {
        this.random = random;
        this.notDrawnCards = initializeNotDrawnCards();
    }

    public Card drawCard() {
        int randomCardIndex = random.nextInt(notDrawnCards.size());
        return notDrawnCards.remove(randomCardIndex);
    }

    private List<Card> initializeNotDrawnCards() {
        List<Rank> ranks = List.of(Rank.values());
        List<Card> notDrawnCards = new ArrayList<>();
        for (Rank rank : ranks) {
            notDrawnCards.addAll(createCardsByRank(rank));
        }
        return notDrawnCards;
    }

    private List<Card> createCardsByRank(Rank rank) {
        List<Shape> shapes = List.of(Shape.values());
        List<Card> cardsByRank = new ArrayList<>();
        for (Shape shape : shapes) {
            cardsByRank.add(new Card(rank, shape));
        }
        return cardsByRank;
    }


}
