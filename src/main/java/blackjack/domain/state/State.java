package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {
    boolean isFinished();

    State draw(Card card);

    Cards getCards();
}
