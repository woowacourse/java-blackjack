package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {
    boolean isFinished();

    State draw(Card card);

    Cards getCards();

    State stay();

    boolean isBlackJack();

    boolean isStay();

    boolean isBust();

    boolean isWin(State state);

    boolean isDraw(State state);
}
