package blackjack.domain.user;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.user.status.Status;

import static blackjack.domain.user.Dealer.DEALER_NAME;

public class User extends Participant {
    private final BettingMoney bettingMoney;

    public User(String name, long bettingMoney){
        super(name);
        whenUserNameEqualsDealerName(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    private void whenUserNameEqualsDealerName(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("딜러는 이름으로 사용할 수 없습니다.");
        }
    }

    public void stopUser() {
        status = Status.STOP;
    }

    public boolean canContinueGame() {
        return status.canContinueGame();
    }

    public BettingMoney getBettingMoney(){
        return this.bettingMoney;
    }
}
