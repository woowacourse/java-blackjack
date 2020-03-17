package domain.result;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResultCalculator {

    private Map<Result, Integer> dealerResult = new HashMap<>();

    public ResultCalculator(){
        Arrays.stream(Result.values())
                .forEach(result -> dealerResult.put(result, 0));
    }

    public DealerResult calculateDealerAndPlayersResult(Dealer dealer, Players players) {
        for(Player player : players.getPlayers()) {
            player.setResult(calculateResult(dealer, player));
        }
        return new DealerResult(this.dealerResult);
    }

    public Result calculateResult(Dealer dealer, Player player) {
        if (isDraw(dealer, player)) {
            dealerResult.replace(Result.DRAW, dealerResult.get(Result.DRAW) + 1);
            return Result.DRAW;
        }
        if (isPlayerLose(dealer, player)) {
            dealerResult.replace(Result.WIN, dealerResult.get(Result.WIN) + 1);
            return Result.LOSE;
        }
        dealerResult.replace(Result.LOSE, dealerResult.get(Result.LOSE) + 1);
        return Result.WIN;
    }

    private boolean isDraw(Dealer dealer, Player player) {
        boolean isBothOverBlackJack = dealer.isLargerThan(Cards.BLACKJACK_SCORE)
            && player.isLargerThan(Cards.BLACKJACK_SCORE);

        return isBothOverBlackJack || dealer.isScoreSame(player.getTotalScore());
    }

    private boolean isPlayerLose(Dealer dealer, Player player) {
        /*
         * 딜러는 파산이 아닐 때
         * 플레이어가 파산했거나, 딜러가 플레이어보다 점수가 높으면 짐.
         * */
        return !dealer.isLargerThan(Cards.BLACKJACK_SCORE)
            && (player.isLargerThan(Cards.BLACKJACK_SCORE)
            || dealer.isLargerThan(player.getTotalScore()));
    }

}
