package blackjack.domain.card.factory;

import blackjack.domain.card.Card;
import java.util.List;

public interface CardFactory {

    List<Card> createCards();
}
