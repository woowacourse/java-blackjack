package blackjack.domain;

import blackjack.domain.card.factory.CardFactory;
import blackjack.domain.card.TrumpCard;
import blackjack.domain.card.factory.TrumpCardFactory;
import blackjack.strategy.ShuffleStrategy;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final String NO_CARD_EXCEPTION = "카드가 부족하여 더 이상 뽑을 수 없습니다.";

    private final List<TrumpCard> trumpCards;

    public Deck(final ShuffleStrategy shuffleStrategy) {
        CardFactory cardFactory = new TrumpCardFactory();
        this.trumpCards = cardFactory.createCards();
        shuffleStrategy.shuffle(trumpCards);
    }

    public TrumpCard drawn() {
        if (trumpCards.isEmpty()) {
            throw new IllegalStateException(NO_CARD_EXCEPTION);
        }
        return trumpCards.remove(0);
    }

    public List<TrumpCard> getCards() {
        return Collections.unmodifiableList(trumpCards);
    }
}
