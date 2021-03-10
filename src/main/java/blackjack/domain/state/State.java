package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.user.Cards;

public interface State {
    State draw(Card card);
    State stay();
    boolean isFinish();
    Cards cards();
}
