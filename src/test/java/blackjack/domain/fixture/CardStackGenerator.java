package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import java.util.LinkedList;
import java.util.List;

public class CardStackGenerator {

    public static CardStack ofReverse(Card... cards) {
        return new CardStackStub(List.of(cards));
    }

    private static class CardStackStub implements CardStack {
        final LinkedList<Card> cards = new LinkedList<>();

        CardStackStub(List<Card> cards) {
            this.cards.addAll(cards);
        }

        public Card pop() {
            Card newCard = cards.pollFirst();
            if (newCard == null) {
                throw new IllegalArgumentException("카드 스택이 비어있습니다!");
            }

            return newCard;
        }
    }
}
