package blackjack.domain.participant;

import blackjack.domain.result.BettingMoney;
import blackjack.domain.result.BlackjackMatch;
import blackjack.domain.result.EmptyBettingMoney;
import blackjack.domain.result.PlayerBettingMoney;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public class Player extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PROHIBIT_NAME = "플레이어의 이름은 딜러일 수 없습니다.";

    private BettingMoney bettingMoney;

    public Player(String name, State state) {
        super(name, state);
        this.bettingMoney = new EmptyBettingMoney();
        validateProhibitName(name);
    }

    public Player(String name) {
        this(name, new Ready());
    }

    private void validateProhibitName(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PROHIBIT_NAME);
        }
    }

    public void betting(BettingMoney money) {
        if (bettingMoney.isEmpty()) {
            bettingMoney = new PlayerBettingMoney(money);
        }
    }

    public double calculateProfit(BlackjackMatch blackjackMatch) {
        return getStatus().profitRate(blackjackMatch) * bettingMoney.getMoney();
    }

    @Override
    public boolean hasNextTurn() {
        return !getStatus().isFinished();
    }

    @Override
    public BlackjackMatch match(Participant dealer) {
        return getStatus().match(dealer.getStatus());
    }
}
