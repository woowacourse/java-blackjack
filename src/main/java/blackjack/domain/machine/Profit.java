package blackjack.domain.machine;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.Map;
import java.util.Objects;

public class Profit {
    private static final double BLACKJACK_RATE = 1.5;
    private static final long INIT_MONEY = 0L;

    private final long money;

    public Profit(long money) {
        this.money = money;
    }

    private Profit() {
        money = INIT_MONEY;
    }

    public static Profit of(Dealer dealer, Player player) {
        if (player.isBlackjack()) {
            return getProfitForBlackjack(dealer, player);
        }

        return getOrdinaryProfit(dealer, player);
    }

    private static Profit getOrdinaryProfit(Dealer dealer, Player player) {
        Record record = Record.getRecord(player, dealer);

        if (record == Record.VICTORY) {
            return new Profit(player.getBettingMoney());
        }

        if (record == Record.DEFEAT) {
            return new Profit(-player.getBettingMoney());
        }

        return new Profit();
    }

    private static Profit getProfitForBlackjack(Dealer dealer, Player player) {
        if (dealer.isBlackjack()) {
            return new Profit(player.getBettingMoney());
        }

        return new Profit((long) (BLACKJACK_RATE * player.getBettingMoney()));
    }

    public static Profit getDealerProfit(Map<Participant, Profit> playersProfit) {
        long sum = playersProfit.values().stream()
                .mapToLong(Profit::getMoney)
                .sum();

        return new Profit(-sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profit)) {
            return false;
        }
        Profit profit = (Profit) o;
        return money == profit.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    public long getMoney() {
        return money;
    }
}
