package blackjack.domain.card.factory;

import blackjack.domain.card.TrumpCard;
import java.util.List;

public interface CardFactory {

    List<TrumpCard> createCards();
}
