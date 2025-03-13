package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum GameResultTypeJudgement {
    DEALER_BUSTED(BustedChecker.DEALER_BUSTED, (playerValue, dealerValue) -> GameResultType.WIN),
    ALL_BUSTED(BustedChecker.ALL_BUSTED, (playerValue, dealerValue) -> GameResultType.TIE),
    PLAYER_BUSTED(BustedChecker.PLAYER_BUSTED, (playerValue, dealerValue) -> GameResultType.LOSE),
    NORMAL(BustedChecker.NONE, GameResultType::find);

    private final BustedChecker bustedChecker;
    private final BiFunction<Integer, Integer, GameResultType> gameResultType;

    GameResultTypeJudgement(BustedChecker bustedChecker, BiFunction<Integer, Integer, GameResultType> gameResultType) {
        this.bustedChecker = bustedChecker;
        this.gameResultType = gameResultType;
    }

    public static GameResultType findForPlayer(Player player, Dealer dealer) {
        int playerValue = player.getOptimisticValue();
        int dealerValue = dealer.getOptimisticValue();

        return Arrays.stream(values())
                .filter(judge -> judge.bustedChecker.check(player, dealer))
                .findFirst()
                .orElse(NORMAL)
                .gameResultType.apply(playerValue, dealerValue);
    }
}
