package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;


public class AllCardsOpenStrategy implements CardOpenStrategy {
    @Override
    public List<Card> getCardsWithStrategy(List<Card> cards) {
        return cards;
    }
}
