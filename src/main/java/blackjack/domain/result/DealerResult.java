package blackjack.domain.result;

import blackjack.domain.player.info.Name;
import blackjack.domain.result.prize.PrizeMoney;

import java.util.List;

public class DealerResult {
    private final Name name;
    private final PrizeMoney prizeMoney;

    private DealerResult(final Name name, final PrizeMoney prizeMoney) {
        this.name = name;
        this.prizeMoney = prizeMoney;
    }

    public static DealerResult of(final Name name, final List<GamePlayerResult> gamePlayerResults) {
        final int totalValue = gamePlayerResults.stream()
                                                .mapToInt(GamePlayerResult::getPrizeMoney)
                                                .sum();
        return new DealerResult(name, new PrizeMoney(totalValue * -1));
    }

    public String getName() {
        return name.asString();
    }

    public int getPrizeMoney() {
        return this.prizeMoney.value();
    }
}
