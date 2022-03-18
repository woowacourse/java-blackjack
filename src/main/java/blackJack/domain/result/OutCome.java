package blackJack.domain.result;

import blackJack.domain.card.Cards;
import blackJack.domain.money.Bet;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum OutCome {
    BLACKJACK(1.5, (dealer, player) ->
            (!dealer.isBlackJack() && player.isBlackJack())
    ),
    WIN(1, (dealer, player) ->
            (!player.isBust() && dealer.isBust()) ||
                    (!player.isBust() && (dealer.addScore() < player.addScore()))
    ),
    DRAW(0, (dealer, player) ->
            (dealer.isBlackJack() && player.isBlackJack()) ||
                    (!dealer.isBlackJack() && !player.isBlackJack() && dealer.addScore() == player.addScore())
    ),
    LOSE(-1, (dealer, player) ->
            (dealer.isBlackJack() && !player.isBlackJack()) ||
                    (dealer.isBust() && player.isBust()) ||
                    (!dealer.isBust() && player.isBust()) ||
                    (!dealer.isBust() && (dealer.addScore() > player.addScore()))
    );

    private final double profit;
    private final BiPredicate<Cards, Cards> predicate;

    OutCome(double profit, BiPredicate<Cards, Cards> predicate) {
        this.profit = profit;
        this.predicate = predicate;
    }

    public static OutCome of(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(dealer.getCardsInfo(), player.getCardsInfo()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    public int calculateEarning(Bet bet) {
        return (int) (bet.getValue() * profit);
    }

    public int calculateReverseEarning(int earning) {
        return (int) (LOSE.getProfit() * earning);
    }

    public double getProfit() {
        return profit;
    }
}
