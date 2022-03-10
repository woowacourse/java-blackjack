package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import java.util.LinkedList;
import java.util.List;

public class CardStackGenerator {

    public static CardStack ofReverse(Card... cards) {
        return new CardStackImpl(List.of(cards));
    }

    private static class CardStackImpl implements CardStack {
        final LinkedList<Card> cards = new LinkedList<>();

        CardStackImpl(List<Card> cards) {
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
