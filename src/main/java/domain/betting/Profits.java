package domain.betting;

import domain.participant.Name;
import domain.participant.Players;
import domain.result.Results;
import domain.result.WinOrLose;
import java.util.LinkedHashMap;
import java.util.Map;

public class Profits {

    private static final String ABSENT_NAME_ERROR_MESSAGE_FORMAT = "[Error] \"%s\" : 이름이 존재하지 않습니다.";
    private static final double BLACK_JACK_BONUS_PROFIT_RATE = 1.5;
    private static final int INT_FOR_NEGATIVE = -1;

    private final Map<Name, Double> maps;

    public Profits(Map<Name, Double> maps) {
        this.maps = Map.copyOf(maps);
    }

    public static Profits generateProfits(Results results, BettingReceipts bettingReceipts, Players players) {
        Map<Name, Double> maps = new LinkedHashMap<>();
        for (Name name : players.getNames()) {
            maps.put(name, calculatePlayerProfit(
                    results.getVersusOfPlayer(name),
                    bettingReceipts.getBettingMoney(name),
                    players.isBlackJackByName(name)
            ));
        }
        return new Profits(maps);
    }

    private static double calculatePlayerProfit(WinOrLose winOrLoseOfPlayer, BettingMoney bettingMoney, boolean isBlackJack) {
        if (winOrLoseOfPlayer == WinOrLose.WIN) {
            return getWinProfit(bettingMoney, isBlackJack);
        }
        if (winOrLoseOfPlayer == WinOrLose.LOSE) {
            return toNegative(bettingMoney.getBettingMoney());
        }
        return 0;
    }

    private static double getWinProfit(BettingMoney BettingMoney, boolean isBlackJack) {
        int bettingMoney = BettingMoney.getBettingMoney();
        if (isBlackJack) {
            return bettingMoney * BLACK_JACK_BONUS_PROFIT_RATE;
        }
        return bettingMoney;
    }

    private static double toNegative(double profit) {
        return profit * INT_FOR_NEGATIVE;
    }

    public double getProfit(Name name) {
        if (!maps.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ABSENT_NAME_ERROR_MESSAGE_FORMAT, name.getName()));
        }
        return maps.get(name);
    }

    public double calculateDealerProfit() {
        double sum = maps.keySet().stream()
                .mapToDouble(maps::get)
                .sum();
        return toNegative(sum);
    }
}
