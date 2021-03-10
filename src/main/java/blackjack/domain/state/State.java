package blackjack.domain.state;

import blackjack.domain.card.Card;

public interface State {
    boolean isEndState();
    double profit();
    void draw(Card card);
    State changeState();
}