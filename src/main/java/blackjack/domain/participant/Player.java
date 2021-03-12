package blackjack.domain.participant;

import blackjack.domain.Game;
import blackjack.domain.card.Card;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Stay;

public class Player extends Participant{
    private final BattingMoney battingMoney;

    public Player(String name, int money) {
        super(name);
        battingMoney = new BattingMoney(money);
    }

    public Integer getProfit(int dealerScore) {
        int expectedProfit = (int) (battingMoney.getMoney() * state.earningsRate());

        if (dealerScore > Game.BLACKJACK_NUMBER && state instanceof Bust) {
            return 0;
        }

        if (dealerScore < Game.BLACKJACK_NUMBER && state instanceof Stay) {
            return getProfitWhenStayState(dealerScore);
        }

        return expectedProfit;
    }

    private Integer getProfitWhenStayState(int dealerScore) {
        int expectedProfit = (int) (battingMoney.getMoney() * state.earningsRate());
        if (getCardsScore() > dealerScore) {
            return expectedProfit;
        }
        return expectedProfit * -1;
    }
}
