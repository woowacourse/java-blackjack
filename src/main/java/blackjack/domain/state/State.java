package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {

    State stand();

    State hit(Card card);

    boolean isBust();

    boolean isBlackjack();

    double prizeRate();

    State judge(State turnFinished);

    Cards getCards();
}
