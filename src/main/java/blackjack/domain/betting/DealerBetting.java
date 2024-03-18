package blackjack.domain.betting;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.Name;

public class DealerBetting {
    private final Name name;
    private final BettingMoney bettingMoney;

    private DealerBetting(final Name name, final BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static DealerBetting of(final PlayerBettings playerBettings, final Dealer dealer) {
        int playerBettingLose = playerBettings.getPlayerBettings().stream()
                .mapToInt(PlayerBetting::getBetting)
                .sum();

        return new DealerBetting(dealer.getName(), new BettingMoney(-playerBettingLose));
    }

    public Name getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
