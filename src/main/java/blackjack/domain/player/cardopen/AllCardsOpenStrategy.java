package blackjack.domain.player.cardopen;

import blackjack.domain.card.Card;
import java.util.List;


public class AllCardsOpenStrategy implements CardOpenStrategy {
    @Override
    public List<Card> getCards(List<Card> cards) {
        return cards;
    }
}
