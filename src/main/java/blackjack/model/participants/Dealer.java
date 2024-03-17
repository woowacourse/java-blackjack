package blackjack.model.participants;

import blackjack.model.results.PlayerProfits;
import blackjack.vo.Money;

public class Dealer extends Participant {
    private static final int STAND_THRESHOLD = 17;

    @Override
    public boolean canHit() {
        return getScore() < STAND_THRESHOLD;
    }

    public Money calculateDealerProfit(PlayerProfits playerProfits) {
        int dealerProfit = -(playerProfits.getProfits().values().stream()
                .mapToInt(Money::value)
                .sum());
        return new Money(dealerProfit);
    }
}
