package blackjack.domain.state;

import blackjack.domain.card.Card;

public interface Status {

    Status draw(Card card);
}
