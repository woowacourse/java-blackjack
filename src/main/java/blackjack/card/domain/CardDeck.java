package blackjack.card.domain;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CardDeck implements CardDrawer {

    private final Stack<Card> blackjackCards;

    private CardDeck(CardCreateStrategy cardCreateStrategy) {
        validate(cardCreateStrategy);
        List<Card> cards = cardCreateStrategy.getCards();

        this.blackjackCards = new Stack<>();
        this.blackjackCards.addAll(cards);
    }

    private void validate(CardCreateStrategy cardCreateStrategy) {
        if (cardCreateStrategy == null) {
            throw new IllegalArgumentException("카드를 생성 할 수 없습니다.");
        }
    }

    public static CardDeck getInstance(CardCreateStrategy cardCreateStrategy) {
        return new CardDeck(cardCreateStrategy);
    }

    @Override
    public Card draw() {
        checkEmpty();
        return blackjackCards.pop();
    }

    private void checkEmpty() {
        if (blackjackCards.isEmpty()) {
            throw new NoSuchElementException("모든 패를 뽑았습니다.");
        }
    }
}
