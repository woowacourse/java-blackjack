package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.Response;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.state.BlackJackState;

public class Player extends BlackJackParticipant {

    private final Money money;

    public Player(String name, String money) {
        super(name);
        this.money = new Money(money);
    }

    @Override
    public void draw(Card card) {
        getHand().addCard(card);
        updateState();
    }

    public boolean willContinue(String input) {
        if (!Response.getHitStatus(input)) {
            stay();
        }
        return isContinue();
    }

    public ResultType match(Dealer dealer) {
        if (isBust()) {
            return ResultType.LOSE;
        }
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        if (getState() instanceof BlackJackState && !(dealer.getState() instanceof BlackJackState)) {
            return ResultType.WIN;
        }

        return ResultType.getResultType(getScore() - dealer.getScore());
    }

    public double getMoney() {
        return money.getValue();
    }

    public double getProfit(Dealer dealer) {
        return getState().profitRate(match(dealer)) * getMoney();
    }
}
