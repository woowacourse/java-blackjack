package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.Response;
import blackjack.domain.card.Card;

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

    public double getProfit(Dealer dealer) {
        return getState().profitRate(dealer, getScore()) * getMoney();
    }

    public double getMoney() {
        return money.getValue();
    }
}
