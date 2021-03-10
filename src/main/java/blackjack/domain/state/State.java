package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {
    boolean isEndState();
    double profit();
    void draw(Card card);
    State changeState();
    State stay();
    Cards getCards();
}