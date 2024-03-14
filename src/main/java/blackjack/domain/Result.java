package blackjack.domain;

import blackjack.domain.participant.BetMoney;
import java.util.function.Function;

public enum Result {

    BLACK_JACK("승", (betMoney) -> (int) (1.5 * betMoney.getBetMoney())),
    WIN("승", BetMoney::getBetMoney),
    LOSE("패", (betMoney) -> -betMoney.getBetMoney()),
    DRAW("무", (betMoney) -> 0);

    private static final int DRAW_PROFIT = 0;

    private final String result;
    private final Function<BetMoney, Integer> profitFormula;

    Result(String result, Function<BetMoney, Integer> profitFormula) {
        this.result = result;
        this.profitFormula = profitFormula;
    }

    public static Result reverseResult(Result result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public int findBetProfit(BetMoney betMoney) {
        return profitFormula.apply(betMoney);
    }

    public String getResult() {
        return result;
    }
}
