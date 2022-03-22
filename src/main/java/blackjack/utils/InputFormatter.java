package blackjack.utils;

import static blackjack.validator.CommonValidator.isBlank;
import static blackjack.validator.CommonValidator.isNumber;

import blackjack.domain.BettingMoney;

public class InputFormatter {

    private InputFormatter() {
    }

    public static int parseInt(String source) {
        isBlank(source);
        isNumber(source);
        return Integer.parseInt(source);
    }

    public static BettingMoney parseBettingMoney(String playerName, String bettingMoney) {
        return new BettingMoney(playerName, parseInt(bettingMoney));
    }
}
