package blackjack.domain.betting;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.Name;

public class DealerBetting {
    private final Name name;
    private final BettingMoney money;

    private DealerBetting(final Name name, final BettingMoney money) {
        this.name = name;
        this.money = money;
    }

    public static DealerBetting of(final PlayerBettings playerBettings, final Dealer dealer) {
        int playerBettingLose = playerBettings.getPlayerBettings().stream()
                .mapToInt(PlayerBetting::getBetting)
                .sum();

        return new DealerBetting(dealer.getName(), new BettingMoney(-playerBettingLose));
    }

    public String getName() {
        return name.getName();
    }

    public int getMoney() {
        return money.getMoney();
    }
}
