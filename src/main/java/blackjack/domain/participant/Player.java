package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.money.Money;

public class Player extends Participant {

    private static final String INVALID_NAME_LENGTH_EXCEPTION_MESSAGE = "이름은 1글자 이상이어야합니다.";

    private final Money betMoney;

    private Player(final String name, final Hand hand, final Money betMoney) {
        super(name, hand);
        this.betMoney = betMoney;
    }

    public static Player of(final String name, final Hand hand, final Money betMoney) {
        validateNameNotEmpty(name);
        return new Player(name, hand, betMoney);
    }

    private static void validateNameNotEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    public void receiveCard(Card card) {
        getHand().add(card);
    }

    public boolean canReceive() {
        Score score = Score.calculateSumFrom(getHand());
        return !score.isBusted();
    }

    public Money calculateProfit(Dealer dealer) {
        ResultType resultType = super.compareWith(dealer);
        return betMoney.calculateProfit(resultType);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + super.getName() +
                ", hand=" + super.getHand() +
                ", betMoney=" + betMoney +
                '}';
    }
}
