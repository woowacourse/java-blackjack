package domain.result;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Player;

public class ResultCalculator {
    public static Result calculatePlayerResult(Dealer dealer, Player player) {
        if (isDraw(dealer, player)) {
            return Result.DRAW;
        }
        if (isPlayerLose(dealer, player)) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    private static boolean isDraw(Dealer dealer, Player player) {
        boolean isBothOverBlackJack = dealer.isLargerThan(Cards.BLACKJACK_SCORE)
            && player.isLargerThan(Cards.BLACKJACK_SCORE);

        return isBothOverBlackJack || dealer.isScoreSame(player.getTotalScore());
    }

    private static boolean isPlayerLose(Dealer dealer, Player player) {
        /*
         * 딜러는 파산이 아닐 때
         * 플레이어가 파산했거나, 딜러가 플레이어보다 점수가 높으면 짐.
         * */
        return !dealer.isLargerThan(Cards.BLACKJACK_SCORE)
            && (player.isLargerThan(Cards.BLACKJACK_SCORE)
            || dealer.isLargerThan(player.getTotalScore()));
    }

}
