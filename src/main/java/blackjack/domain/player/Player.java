package blackjack.domain.player;

import blackjack.domain.card.Cards;

public class Player extends Participant {

    private BettingMoney bettingMoney;

    public Player(String name, Cards cards) {
        super(name, cards);
    }

    public void betMoney(int money) {
        checkBetMoneyAlready();
        this.bettingMoney = new BettingMoney(money);
    }

    private void checkBetMoneyAlready() {
        if (bettingMoney != null) {
            throw new IllegalStateException("베팅은 한 번만 할 수 있습니다.");
        }
    }

    public void stay() {
        state = state.stay();
    }

    public double findProfit(Dealer dealer) {
        return state.profit(dealer, getBettingMoney());
    }

    public boolean isHit(DrawStatus drawStatus) {
        return drawStatus == DrawStatus.YES;
    }

    public int getBettingMoney() {
        return bettingMoney.getValue();
    }
}
