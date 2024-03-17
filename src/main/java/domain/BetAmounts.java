package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import exception.BetAmountRangeException;
import exception.BetAmountUnitException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BetAmounts {
    public static final int BET_AMOUNT_UNIT = 100;
    public static final int BET_MIN_AMOUNT = 1_000;
    public static final int BET_MAX_AMOUNT = 1_000_000;

    private final Map<Player, Integer> amounts;

    public BetAmounts() {
        this.amounts = new LinkedHashMap<>();
    }

    public void addBetAmount(final Player player, final int amount) {
        validateBetAmountRange(amount);
        validateBetAmountUnit(amount);
        amounts.put(player, amount);
    }

    private void validateBetAmountRange(final int amount) {
        if (amount < BET_MIN_AMOUNT || amount > BET_MAX_AMOUNT) {
            throw new BetAmountRangeException(BetAmountRangeException.INVALID_AMOUNT_RANGE);
        }
    }

    private void validateBetAmountUnit(final int amount) {
        if (amount % BET_AMOUNT_UNIT != 0) {
            throw new BetAmountUnitException(BetAmountUnitException.INVALID_BET_AMOUNT_UNIT);
        }
    }

    public int calculateDealerBetProfit(final Player player, final Dealer dealer) {
        return calculatePlayerBetProfit(player, dealer) * -1;
    }

    public int calculatePlayerBetProfit(final Player player, final Dealer dealer) {
        Result playerResult = Result.getPlayerResultWith(player, dealer);
        return playerResult.calculateProfit(amounts.get(player));
    }
}
