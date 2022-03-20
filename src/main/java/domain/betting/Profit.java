package domain.betting;

import domain.participant.Name;
import domain.participant.Players;
import domain.result.Result;
import domain.result.Versus;
import java.util.LinkedHashMap;
import java.util.Map;

public class Profit {

    private static final String ABSENT_NAME_ERROR_MESSAGE_FORMAT = "[Error] \"%s\" : 이름이 존재하지 않습니다.";
    private static final double BLACK_JACK_BONUS_PROFIT_RATE = 1.5;
    private static final int DEFAULT_PROFIT_RATE = 1;

    private final Map<Name, Double> maps;

    public Profit(Map<Name, Double> maps) {
        this.maps = Map.copyOf(maps);
    }

    public static Profit generateProfits(Result result, BettingReceipt bettingReceipt, Players players) {
        Map<Name, Double> profits = new LinkedHashMap<>();
        for (Name name : players.getNames()) {
            profits.put(name, calculatePlayerProfit(
                    result.getVersusOfPlayer(name),
                    bettingReceipt.getBettingMoney(name),
                    players.isBlackJackByName(name))
            );
        }
        return new Profit(profits);
    }

    private static double calculatePlayerProfit(Versus versusOfPlayer, BettingMoney bettingMoney, boolean isBlackJack) {
        if (versusOfPlayer == Versus.WIN) {
            return bettingMoney.getBettingMoney() * getBlackJackBonusRate(isBlackJack);
        }
        if (versusOfPlayer == Versus.LOSE) {
            return bettingMoney.getBettingMoney() * (-1);
        }
        return 0;
    }

    private static double getBlackJackBonusRate(boolean isBlackJack) {
        if (isBlackJack) {
            return BLACK_JACK_BONUS_PROFIT_RATE;
        }
        return DEFAULT_PROFIT_RATE;
    }

    public double getProfit(Name name) {
        if (!maps.containsKey(name)) {
            throw new IllegalArgumentException(String.format(ABSENT_NAME_ERROR_MESSAGE_FORMAT, name.getName()));
        }
        return maps.get(name);
    }

    public double calculateDealerProfit() {
        return maps.keySet().stream()
                .mapToDouble(maps::get)
                .sum() * (-1);
    }
}
