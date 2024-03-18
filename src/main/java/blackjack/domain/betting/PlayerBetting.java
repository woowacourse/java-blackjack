package blackjack.domain.betting;

import blackjack.domain.Name;

public class PlayerBetting {
    private final Name name;
    private final BettingMoney bettingMoney;

    private PlayerBetting(final Name name, final BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    PlayerBetting(final String name, final int bettingMoney) {
        this(new Name(name), new BettingMoney(bettingMoney));
    }

    public static PlayerBetting create(final String name, final int betting) {
        validateInitialBetting(betting);
        return new PlayerBetting(name, betting);
    }

    private static void validateInitialBetting(final int betting) {
        if (betting <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 커야합니다.");
        }
    }

    public PlayerBetting applyWinStatus(final double betMultiplier) {
        BettingMoney bettingMoney = this.bettingMoney.multiply(betMultiplier);
        return new PlayerBetting(this.name, bettingMoney);
    }

    public boolean equalsName(final Name otherName) {
        return this.name.equals(otherName);
    }

    public String getName() {
        return name.getName();
    }

    public int getBetting() {
        return bettingMoney.getMoney();
    }
}
