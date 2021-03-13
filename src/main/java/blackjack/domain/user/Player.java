package blackjack.domain.user;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    public long getProfit(Dealer dealer) {
        return state.getProfit(dealer, bettingMoney);
    }
}
