package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.factory.CardFactory;
import blackjack.strategy.ShuffleStrategy;
import java.util.Queue;

public class Deck {

    private static final String NO_CARD_EXCEPTION = "카드가 부족하여 더 이상 뽑을 수 없습니다.";

    private final Queue<Card> cards;

    public Deck(final ShuffleStrategy shuffleStrategy) {
        CardFactory cardFactory = new CardFactory();
        this.cards = shuffleStrategy.shuffle(cardFactory.createCards());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(NO_CARD_EXCEPTION);
        }
        return cards.poll();
    }

    public Queue<Card> getCards() {
        return cards;
    }
}
