package domain.card.providable;

import domain.card.Card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck implements CardProvidable {
    private final Stack<Card> cards = new Stack<>();

    public CardDeck() {
        cards.addAll(Card.getAllCards());
        Collections.shuffle(cards);
    }

    @Override
    public Card giveCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
        }

        return cards.pop();
    }
}
