package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class StubDeck {

    private final LinkedList<Card> cards;

    public StubDeck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public Card draw() {
        try {
            return cards.pop();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("[ERROR] 52장의 카드가 모두 소진되었습니다.");
        }
    }
}
