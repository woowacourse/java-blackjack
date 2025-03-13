package blackjack.domain.card;

public class BettingResult {

    public static int getMultiplyRatio(Score score) {
        if (score.value() > 21) {
            return -1;
        }
        return 0;
    }

//    public static BettingResult create(GameResult gameResult) {
//        Map<Player, WinningResult> playerGameResults = gameResult.getPlayerGameResults();
//
//
//    }
}
