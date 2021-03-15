package blackjack.domain;

public class Player extends User {

    private BettingMoney bettingMoney;

    public Player(String name) {
        super(name);
    }

    public void stay() {
        this.state = state.stay();
    }

    public void betting(int bettingMoney) {
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public int getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }
}
