package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;

public interface State {
    public State draw(Card card);
    public State stay();
    public boolean isFinished();
    public UserDeck getUserDeck();
    public double getProfitRate(State dealerState);
    public int getPoint();
}
