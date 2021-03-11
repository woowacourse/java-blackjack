package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {
    boolean isEndState();
    boolean isBust();
    boolean isBlackJack();
    double profit(State enemyState);
    void draw(Card card);
    State changeState();
    State stay();
    Cards getCards();
}