package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public interface State {

    State draw(Card card);

    Cards getCards();

    State stay();
}
