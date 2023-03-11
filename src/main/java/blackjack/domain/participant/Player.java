package blackjack.domain.participant;

public class Player extends Participant {

    private final int BUST_POINT = 21;
    private final BettingMoney bettingMoney;

    private final Name name;

    Player(final String name, final int bettingMoney) {
        this.name = new Name(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore < BUST_POINT;
    }

    public String getName() {
        return name.getValue();
    }

    boolean hasName(final String playerName) {
        return name.getValue()
                .equals(playerName);
    }

    public int calculateProfit(final double profit) {
        return bettingMoney.profit(profit);
    }
}
