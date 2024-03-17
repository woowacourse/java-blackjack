package blackjack.domain.betting;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.participant.ParticipantName;

public class DealerBetting {
    private final ParticipantName name;
    private final BettingMoney bettingMoney;

    private DealerBetting(final ParticipantName name, final BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static DealerBetting of(final PlayerBettings playerBettings, final Dealer dealer) {
        int playerBettingLose = playerBettings.getPlayerBettings().stream()
                .mapToInt(PlayerBetting::getBetting)
                .sum();

        return new DealerBetting(dealer.getName(), new BettingMoney(-playerBettingLose));
    }

    public ParticipantName getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
