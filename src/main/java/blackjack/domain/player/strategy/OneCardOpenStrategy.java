package blackjack.domain.player.strategy;

import blackjack.domain.card.Card;
import java.util.List;


public class OneCardOpenStrategy implements CardOpenStrategy {
    private static final int ONE_CARD_SIZE = 1;

    @Override
    public List<Card> getCardsWithStrategy(List<Card> cards) {
        return cards.subList(0, ONE_CARD_SIZE);
    }
}
