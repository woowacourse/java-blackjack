package repository;

import domain.model.Card;

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
}
