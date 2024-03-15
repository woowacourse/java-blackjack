package blackjack.domain;

import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Hands;
import blackjack.domain.participant.Player;
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

    public static Result of(Dealer dealer, Player player) {
        Hands dealerHands = dealer.getHands();
        Hands playerHands = player.getHands();
        if (isDraw(dealerHands, playerHands)) {
            return Result.DRAW;
        }
        if (playerHands.isBlackJack()) {
            return Result.BLACK_JACK;
        }
        if (isWin(dealerHands, playerHands)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private static boolean isDraw(Hands dealerHands, Hands playerHands) {
        return (dealerHands.isBust() && playerHands.isBust())
                || (playerHands.isSameScore(dealerHands));
    }

    private static boolean isWin(Hands dealerHands, Hands playerHands) {
        return dealerHands.isBust()
                || ((playerHands.isHigherScore(dealerHands)) && !playerHands.isBust());
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
