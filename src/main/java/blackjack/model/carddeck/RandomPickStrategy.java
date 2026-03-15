package blackjack.model.carddeck;

import blackjack.common.error.ErrorCode;
import blackjack.model.card.Card;
import java.util.List;
import java.util.Random;

public class RandomPickStrategy implements PickStrategy {

    private static final Random RANDOM = new Random();

    @Override
    public Card pick(final List<Card> cards) {
        validateCards(cards);
        int randomIndex = RANDOM.nextInt(cards.size());
        return cards.remove(randomIndex);
    }

    private void validateCards(final List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException(ErrorCode.NULL_OR_EMPTY_CARDS.getMessage());
        }
    }
}
