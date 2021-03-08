package blackjack.domain.player.cardopen;

import blackjack.domain.card.Card;
import java.util.List;

public interface CardOpenStrategy {
    List<Card> getCards(List<Card> cards);
}
