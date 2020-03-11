package domain;

import domain.card.Card;

import java.util.Collections;
import java.util.List;

public class CardDeck implements CardProvider{
    private static final int FIRST = 0;
    private final List<Card> cards;

    public CardDeck() {
        cards = Card.getAllCards();
        Collections.shuffle(cards);
    }

    @Override
    public Card giveCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
        }

        return cards.remove(FIRST);
    }
}
