package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.factory.CardFactory;
import blackjack.domain.card.factory.TrumpCardFactory;
import blackjack.strategy.ShuffleStrategy;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final String NO_CARD_EXCEPTION = "카드가 부족하여 더 이상 뽑을 수 없습니다.";

    private final List<Card> cards;

    public Deck(final ShuffleStrategy shuffleStrategy) {
        CardFactory cardFactory = new TrumpCardFactory();
        this.cards = cardFactory.createCards();
        shuffleStrategy.shuffle(cards);
    }

    public Card drawn() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(NO_CARD_EXCEPTION);
        }
        return cards.remove(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
