package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayRecord;

public interface State {

    State draw(Card card);

    Cards getCards();

    State stay();

    boolean isDrawable();

    long revenue(PlayRecord playRecord, Bet bet);
}
