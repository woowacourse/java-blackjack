package blackjack.domain.bet;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersBet {

    public static final double BLACKJACK_PROFIT_RATE = 0.5;
    public static final int WIN_PROFIT_RATE = 1;
    public static final int LOSE_PROFIT_RATE = -1;

    private final Map<Player, Money> participantBetTable;

    public PlayersBet() {
        participantBetTable = new LinkedHashMap<>();
    }

    public void add(Player player, Money money) {
        participantBetTable.put(player, money);
    }

    public Money get(Player player) {
        return participantBetTable.get(player);
    }

    public Map<Participant, Money> calculateHitProfit(Map<Player, Result> judgeResult, Participant dealer) {
        Map<Participant, Money> profitTable = new LinkedHashMap<>();
        giveProfit(judgeResult, profitTable);
        calculateDealerMoney(profitTable, dealer);
        return profitTable;
    }

    private void giveProfit(Map<Player, Result> judgeResult, Map<Participant, Money> profitTable) {
        for (Player player : judgeResult.keySet()) {
            if (judgeResult.get(player) == Result.Blackjack) {
                profitTable.put(player, participantBetTable.get(player).multiply(BLACKJACK_PROFIT_RATE));
            }
            if (judgeResult.get(player) == Result.WIN) {
                profitTable.put(player, participantBetTable.get(player).multiply(WIN_PROFIT_RATE));
            }
            if (judgeResult.get(player) == Result.LOSE) {
                profitTable.put(player, participantBetTable.get(player).multiply(LOSE_PROFIT_RATE));
            }
        }
    }

    private void calculateDealerMoney(Map<Participant, Money> profitTable,
                                      Participant dealer) {
        Money dealerMoney = new Money(-profitTable.values().stream()
                .map(Money::getValue)
                .reduce(0, Integer::sum));
        profitTable.put(dealer, dealerMoney);
    }
}
