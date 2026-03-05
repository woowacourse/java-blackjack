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

    public boolean isExist(Card card) {
        return cards.stream().anyMatch(value -> value.isSameCard(card));
    }

    public boolean isExistByShapeAndRank(CardRank cardRank, CardShape cardShape) {
        for (Card card : cards) {
            // TODO: depth2 -> depth1으로 리팩토링
            if (card.getCardRank() == cardRank && card.getCardShape() == cardShape) {
                return true;
            }
        }
        return false;
    }
}
