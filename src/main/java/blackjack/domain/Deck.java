package blackjack.domain;

import blackjack.domain.card.TrumpCard;
import blackjack.domain.card.TrumpCardFactory;
import blackjack.strategy.ShuffleStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private static final String NO_CARD_EXCEPTION = "카드가 부족하여 더 이상 뽑을 수 없습니다.";

    private final Queue<TrumpCard> trumpCards;

    public Deck(final ShuffleStrategy shuffleStrategy) {
        List<TrumpCard> trumpCards = TrumpCardFactory.createCards();
        shuffleStrategy.shuffle(trumpCards);

        this.trumpCards = new LinkedList<>(trumpCards);
    }

    public TrumpCard draw() {
        if (trumpCards.isEmpty()) {
            throw new IllegalStateException(NO_CARD_EXCEPTION);
        }

        return trumpCards.poll();
    }
}
