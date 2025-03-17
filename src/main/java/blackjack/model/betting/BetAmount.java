package blackjack.model.betting;

import java.util.Objects;

public class BetAmount {

    private static final int MINIMUM_STAKE = 1_000;
    private static final int MAX_STAKE = 1_000_000;

    private final int stake;

    public BetAmount(int stake) {
        validateStake(stake);
        this.stake = stake;
    }

    private void validateStake(int stake) {
        if (stake < MINIMUM_STAKE || stake > MAX_STAKE) {
            throw new IllegalArgumentException("배팅 금액은 1000원 단위여야하고, 1,000원 ~ 1,000,000원까지 가능합니다.");
        }
        if (stake % 1000 != 0) {
            throw new IllegalArgumentException("배팅 금액은 1000원 단위여야하고, 1,000원 ~ 1,000,000원까지 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BetAmount betAmount = (BetAmount) o;
        return stake == betAmount.stake;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stake);
    }

    public int getStake() {
        return stake;
    }
}
