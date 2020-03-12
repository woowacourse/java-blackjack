package blackjack.card.domain;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> blackjackCards;

    private CardDeck(CardCreateStrategy cardCreateStrategy) {
        List<Card> cards = cardCreateStrategy.getCards();
        Collections.shuffle(cards);

        this.blackjackCards = new Stack<>();
        this.blackjackCards.addAll(cards);
    }

    public static CardDeck getInstance(CardCreateStrategy cardCreateStrategy) {
        return new CardDeck(cardCreateStrategy);
    }

    public Card drawCard() {
        checkEmpty();
        return blackjackCards.pop();
    }

    private void checkEmpty() {
        if (blackjackCards.isEmpty()) {
            throw new NoSuchElementException("모든 패를 뽑았습니다.");
        }
    }
}
