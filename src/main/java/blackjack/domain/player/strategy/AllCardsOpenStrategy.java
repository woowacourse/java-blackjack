package blackjack.domain.player.strategy;

import blackjack.domain.card.Card;
import java.util.List;


public class AllCardsOpenStrategy implements CardOpenStrategy {
    @Override
    public List<Card> getCardsWithStrategy(List<Card> cards) {
        return cards;
    }
}
