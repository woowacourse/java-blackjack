package blackjack.domain.result;

import blackjack.domain.player.info.Name;
import blackjack.domain.result.prize.PrizeMoney;

public class GamePlayerResult {
    private final Name name;
    private final PrizeMoney prizeMoney;

    public GamePlayerResult(final Name name, final PrizeMoney prizeMoney) {
        this.name = name;
        this.prizeMoney = prizeMoney;
    }

    public String getName() {
        return name.asString();
    }

    public int getPrizeMoney() {
        return this.prizeMoney.value();
    }
}
