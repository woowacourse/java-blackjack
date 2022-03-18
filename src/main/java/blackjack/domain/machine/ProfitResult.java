package blackjack.domain.machine;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProfitResult {
    private final Map<Participant, Profit> result;

    private ProfitResult(Map<Participant, Profit> result) {
        this.result = result;
    }

    public static ProfitResult of(Dealer dealer, Players players) {
        Map<Participant, Profit> result = players.getPlayers()
                .stream()
                .collect(Collectors.toMap(player -> player, player -> getProfit(dealer, player)));
        result.put(dealer, getDealerProfit(result));

        return new ProfitResult(result);
    }

    private static Profit getProfit(Dealer dealer, Player player) {
        if (player.initScore().isBlackjack()) {
            return getProfitForBlackjack(dealer, player);
        }

        return getOrdinaryProfit(dealer, player);
    }

    private static Profit getOrdinaryProfit(Dealer dealer, Player player) {
        Record record = Record.getRecord(player, dealer);

        if (record == Record.VICTORY) {
            return new Profit(player.getBetting().getMoney());
        }

        if (record == Record.DEFEAT) {
            return new Profit(-player.getBetting().getMoney());
        }

        return new Profit(0L);
    }

    private static Profit getProfitForBlackjack(Dealer dealer, Player player) {
        if (dealer.initScore().isBlackjack()) {
            return new Profit(player.getBetting().getMoney());
        }

        return new Profit((long) (1.5 * player.getBetting().getMoney()));
    }

    private static Profit getDealerProfit(Map<Participant, Profit> playersProfit) {
       long sum =  playersProfit.values().stream()
                .mapToLong(Profit::getMoney)
                .sum();

       return new Profit(-sum);
    }

    public Map<Participant, Profit> getResult() {
        return result;
    }
}
