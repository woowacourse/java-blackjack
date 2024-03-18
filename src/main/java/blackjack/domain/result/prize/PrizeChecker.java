package blackjack.domain.result.prize;

import blackjack.domain.card.state.BlackjackStatus;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Participant;

import java.util.function.BiPredicate;


public enum PrizeChecker {
    BLACKJACK(PrizeChecker::isPlayerBlackjack, 1.5),
    WIN((gamePlayer, dealer) -> isDealerBust(gamePlayer, dealer) || isPlayerScoreWin(gamePlayer, dealer), 1),
    DRAW(((gamePlayer, dealer) -> isBothBlackjack(gamePlayer, dealer) || isPlayerScoreDraw(gamePlayer, dealer)), 0),
    LOSE((gamePlayer, dealer) -> isBust(gamePlayer) || isPlayerScoreLose(gamePlayer, dealer), -1);
    private final BiPredicate<GamePlayer, Dealer> predicate;
    private final double profitRate;

    PrizeChecker(final BiPredicate<GamePlayer, Dealer> predicate, final double profitRate) {
        this.predicate = predicate;
        this.profitRate = profitRate;
    }

    public static final PrizeMoney check(final GamePlayer gamePlayer, final Dealer dealer) {
        for (final var checker : PrizeChecker.values()) {
            if (checker.predicate.test(gamePlayer, dealer)) {
                return new PrizeMoney(gamePlayer.getBettingMoney() * checker.profitRate);
            }
        }
        throw new IllegalStateException("조건에 맞지 않는 계산입니다");
    }

    private static boolean isPlayerBlackjack(final GamePlayer gamePlayer, final Dealer dealer) {
        return gamePlayer.getStatus() == BlackjackStatus.BLACKJACK && dealer.getStatus() != BlackjackStatus.BLACKJACK;
    }

    private static boolean isDealerBust(final GamePlayer gamePlayer, final Dealer dealer) {
        return dealer.getStatus() == BlackjackStatus.BUST && gamePlayer.getStatus() != BlackjackStatus.BUST;
    }

    private static boolean isBothBlackjack(final GamePlayer gamePlayer, final Dealer dealer) {
        return gamePlayer.getStatus() == BlackjackStatus.BLACKJACK && dealer.getStatus() == BlackjackStatus.BLACKJACK;
    }

    private static boolean isPlayerScoreWin(final GamePlayer gamePlayer, final Dealer dealer) {
        return isStand(gamePlayer) && compare(gamePlayer, dealer) > 0;
    }

    private static boolean isPlayerScoreDraw(final GamePlayer gamePlayer, final Dealer dealer) {
        return isStand(gamePlayer) && compare(gamePlayer, dealer) == 0;
    }

    private static boolean isPlayerScoreLose(final GamePlayer gamePlayer, final Dealer dealer) {
        return isStand(gamePlayer) && compare(gamePlayer, dealer) < 0;
    }


    private static boolean isStand(final Participant participant) {
        return participant.getStatus() == BlackjackStatus.STAND;
    }

    private static boolean isBust(final Participant participant) {
        return participant.getStatus() == BlackjackStatus.BUST;
    }

    private static int compare(final GamePlayer gamePlayer, final Dealer dealer) {
        return Integer.compare(gamePlayer.calculateScore(), dealer.calculateScore());
    }
}
