package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum GameResult {

    BLACKJACK("패", (dealer, gamblers) -> isGamblerBlackJack(gamblers), betMoney -> betMoney * 2),
    WIN("승", GameResult::isDealerWin, betMoney -> betMoney * -1),
    DRAW("무", GameResult::isDraw, betMoney -> 0),
    LOSE("패", GameResult::isDealerLose, betMoney -> betMoney),
    ;

    private final String result;
    private final BiPredicate<Player, Player> condition;
    private final Function<Integer, Integer> calculateProfit;


    GameResult(final String result, final BiPredicate<Player, Player> condition,
               final Function<Integer, Integer> calculateProfit) {
        this.result = result;
        this.condition = condition;
        this.calculateProfit = calculateProfit;
    }

    public static GameResult of(final Player dealer, final Player gambler) {
        if (containsBurst(dealer, gambler)) {
            return getBurstResult(dealer, gambler);
        }
        return Arrays.stream(values())
            .filter(it -> it.condition.test(dealer, gambler))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 점수가 입력되었습니다."));
    }

    private static boolean isGamblerBlackJack(Player gamblers) {
        return gamblers.isBlackjack();
    }

    private static boolean containsBurst(final Player dealer, final Player gambler) {
        return dealer.isBurst() || gambler.isBurst();
    }

    private static GameResult getBurstResult(final Player dealer, final Player gambler) {
        if (dealer.isBurst() && gambler.isBurst()) {
            return DRAW;
        }
        if (!dealer.isBurst() && gambler.isBurst()) {
            return WIN;
        }
        if (dealer.isBurst() && gambler.isBlackjack()){
            return BLACKJACK;
        }
        return LOSE;
    }

    private static boolean isDealerWin(final Player dealer, final Player gambler) {
        return dealer.isWin(gambler);
    }

    private static boolean isDraw(final Player dealer, final Player gambler) {
        return dealer.isDraw(gambler);
    }

    private static boolean isDealerLose(final Player dealer, final Player gambler) {
        return dealer.isLose(gambler);
    }

    public GameResult reverse() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return this;
    }

    public String getResult() {
        return result;
    }

    public Integer calculateProfit(final int betMoney) {
        return this.calculateProfit.apply(betMoney);
    }
}
