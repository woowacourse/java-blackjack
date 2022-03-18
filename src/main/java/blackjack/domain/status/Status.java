package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public interface Status {

    Status draw(Card card);

    Cards getCards();
}
