package blackjack.domain.user;

public class Player extends User {
    public static final int INITIAL_COUNT = 2;

    public Player(String name) {
        super(name);
    }

    public long getProfit(Dealer dealer) {
        return state.getProfit(dealer, bettingMoney);
    }

    @Override
    public Cards showInitialCard() {
        return state.cards()
                .getCardsByCount(INITIAL_COUNT);
    }
}
