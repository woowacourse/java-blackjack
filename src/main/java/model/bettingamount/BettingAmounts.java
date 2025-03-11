package model.bettingamount;

import java.util.Map;

public class BettingAmounts {
    private final Map<String, BettingAmount> bettingAmounts;

    public BettingAmounts(final Map<String, BettingAmount> bettingAmounts) {
        this.bettingAmounts = bettingAmounts;
    }

    public BettingAmount findByName(String name) {
        if (bettingAmounts.containsKey(name)) {
            return bettingAmounts.get(name);
        }
        throw new IllegalArgumentException("해당하는 이름의 플레이어가 없습니다.");
    }
}
