package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;

import java.util.function.BiPredicate;

public enum ResultChecker {
    LOSE((dealer, player) -> player.isBust() || (player.calculateScore() < dealer.calculateScore() && !dealer.isBust()), ResultStatus.LOSE),
    WIN((dealer, player) -> dealer.isBust() || (player.calculateScore() > dealer.calculateScore() && !player.isBust()), ResultStatus.WIN),
    DRAW((dealer, player) -> player.calculateScore() == dealer.calculateScore(), ResultStatus.DRAW),
    ;

    private final BiPredicate<Dealer, GamePlayer> predicate;
    private final ResultStatus resultStatus;


    ResultChecker(final BiPredicate<Dealer, GamePlayer> predicate, final ResultStatus resultStatus) {
        this.predicate = predicate;
        this.resultStatus = resultStatus;
    }

    public static ResultStatus calculate(final Dealer dealer, final GamePlayer gamePlayer) {
        for (final ResultChecker resultChecker : values()) {
            if (resultChecker.predicate.test(dealer, gamePlayer)) {
                return resultChecker.resultStatus;
            }
        }
        throw new IllegalStateException("조건에 맞지 않는 계산입니다");
    }
}
