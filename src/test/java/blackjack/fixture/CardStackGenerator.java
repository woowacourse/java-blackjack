package blackjack.fixture;

import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER3;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import java.util.LinkedList;
import java.util.List;

public class CardStackGenerator {

    private static final List<Card> SINGLE_PLAYER_CARDS = List.of(CLOVER2, CLOVER3);
    private static final String EMPTY_CARD_STACK_EXCEPTION_MESSAGE = "카드 스택이 비어있습니다!";

    public static CardStack fromDealerCards(final List<Card> dealerCards,
                                            final Card drawableCard) {
        LinkedList<Card> cards = new LinkedList<>();

        cards.addAll(dealerCards);
        cards.addAll(SINGLE_PLAYER_CARDS);
        cards.add(drawableCard);

        return new CardStackImpl(cards);
    }

    private static class CardStackImpl implements CardStack {
        final LinkedList<Card> cards;

        CardStackImpl(LinkedList<Card> cards) {
            this.cards = cards;
        }

        public Card pop() {
            validateStackSize();
            return cards.pollFirst();
        }

        private void validateStackSize() {
            if (cards.isEmpty()) {
                throw new IllegalArgumentException(EMPTY_CARD_STACK_EXCEPTION_MESSAGE);
            }
        }
    }
}
