package blackjack.domain;

import blackjack.domain.card.TrumpCard;
import blackjack.domain.card.factory.CardFactory;
import blackjack.domain.card.factory.TrumpCardFactory;
import blackjack.strategy.ShuffleStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private static final String NO_CARD_EXCEPTION = "카드가 부족하여 더 이상 뽑을 수 없습니다.";

    private final Queue<TrumpCard> trumpCards;

    public Deck(final ShuffleStrategy shuffleStrategy) {
        CardFactory cardFactory = new TrumpCardFactory();
        List<TrumpCard> trumpCards = cardFactory.createCards();

        shuffleStrategy.shuffle(trumpCards);

        this.trumpCards = new LinkedList<>(trumpCards);
    }

    public TrumpCard drawn() {
        if (trumpCards.isEmpty()) {
            throw new IllegalStateException(NO_CARD_EXCEPTION);
        }

        return trumpCards.poll();
    }

    public List<TrumpCard> getCards() {
        return new LinkedList<>(trumpCards);
    }
}
