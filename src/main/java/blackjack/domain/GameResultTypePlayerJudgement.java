package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum GameResultTypePlayerJudgement {
    PLAYER_BUSTED(BustedChecker.PLAYER_BUSTED, (player, dealer) -> GameResultType.LOSE),
    DEALER_BUSTED(BustedChecker.DEALER_BUSTED, (player, dealer) -> GameResultType.WIN),
    ALL_BUSTED(BustedChecker.ALL_BUSTED, (player, dealer) -> GameResultType.TIE),
    NORMAL(BustedChecker.NONE, GameResultType::judgeForPlayer);

    private final BustedChecker bustedChecker;
    private final BiFunction<Player, Dealer, GameResultType> gameResultType;

    GameResultTypePlayerJudgement(BustedChecker bustedChecker,
                                  BiFunction<Player, Dealer, GameResultType> gameResultType) {
        this.bustedChecker = bustedChecker;
        this.gameResultType = gameResultType;
    }

    public static GameResultType find(Player player, Dealer dealer) {
        return Arrays.stream(values())
                .filter(judge -> judge.bustedChecker.check(player, dealer))
                .findFirst()
                .orElse(NORMAL)
                .gameResultType.apply(player, dealer);
    }
}
