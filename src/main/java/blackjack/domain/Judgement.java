package blackjack.domain;

import java.util.function.Function;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Judgement {

    BLACKJACK(money -> money.calculateProfit(1.5)),
    WIN(money -> money.calculateProfit(1)),
    DRAW(money -> money.calculateProfit(1)),
    LOSE(money -> money.calculateProfit(-1));

    private final Function<Money, Profit> profitFunction;

    Judgement(Function<Money, Profit> profitFunction) {
        this.profitFunction = profitFunction;
    }

    public static Judgement judgePlayer(Player player, Dealer dealer) {
        if (dealer.isBlackJack() || player.isBlackJack()) {
            return judgePlayerByBlackJack(player, dealer);
        }
        if (player.isBust()) {
            return Judgement.LOSE;
        }
        if (dealer.isBust()) {
            return Judgement.WIN;
        }
        return judgePlayerByScore(player, dealer);
    }

    private static Judgement judgePlayerByBlackJack(Player player, Dealer dealer) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return Judgement.DRAW;
        }
        if (dealer.isBlackJack()) {
            return Judgement.LOSE;
        }
        return Judgement.BLACKJACK;
    }

    private static Judgement judgePlayerByScore(Player player, Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();
        if (dealerScore == playerScore) {
            return Judgement.DRAW;
        }
        if (dealerScore > playerScore) {
            return Judgement.LOSE;
        }
        return Judgement.WIN;
    }

    public Profit calculateProfit(Money betMoney) {
        return profitFunction.apply(betMoney);
    }
}
