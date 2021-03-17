package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;
import blackjack.domain.money.Money;
import blackjack.domain.state.State;

public abstract class User {

    private State state;
    private Money money;

    public User(State state, Money money) {
        this.state = state;
        this.money = money;
    }

    public void draw(Card card) {
        this.state = state.draw(card);
    }

    public boolean isAvailableDraw() {
        return !state.isFinished();
    }

    public double getProfitRate(Dealer dealer) {
        State dealerState = dealer.getState();
        return this.state.getProfitRate(dealerState);
    }

    protected void calculateMoneyResult(int resultRawMoney) {
        int initialMoneyValue = money.getValue();
        this.money = new Money(initialMoneyValue + resultRawMoney);
    }

    public UserDeck getUserDeck() {
        return state.getUserDeck();
    }

    public int getPoint() {
        return state.getUserDeck().score();
    }

    public Money getMoney() {
        return money;
    }

    public State getState() {
        return state;
    }
}
