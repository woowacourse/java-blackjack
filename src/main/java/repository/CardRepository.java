package repository;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {

    private final List<Card> cards = new ArrayList<>();

    public Card save(Card card) {
        cards.add(card);
        return card;
    }

    public int getCardCount() {
        return cards.size();
    }

    public boolean isExistByShapeAndRank(CardRank cardRank, CardShape cardShape) {
        return cards.stream()
                .anyMatch(card -> matchesRankAndShape(card, cardRank, cardShape));
    }

    private boolean matchesRankAndShape(Card card, CardRank cardRank, CardShape cardShape) {
        return card.getCardRank() == cardRank && card.getCardShape() == cardShape;
    }
}
