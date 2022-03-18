package blackJack.domain;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.User;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    BlackJack("승", (dealer, player) -> !dealer.isBlackJack() && player.isBlackJack()),
    LOSE("패", (dealer, player) -> player.isBurst() || !dealer.isBurst() && dealer.getScore() > player.getScore()),
    WIN("승", (dealer, player) -> dealer.isBurst() && !player.isBurst() || !player.isBurst() && dealer.getScore() < player.getScore()),
    DRAW("무",(dealer, player) -> !player.isBurst() && dealer.getScore() == player.getScore());

    private final String printFormat;
    private final BiPredicate<Dealer, Player> predicate;

    Result(String value, BiPredicate<Dealer, Player> predicate) {
        this.printFormat = value;
        this.predicate = predicate;
    }

    public static Result judge(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(dealer,player))
                .findFirst()
                .get();
    }

    public static Result reverse(Result result) {
        if (result.equals(Result.WIN) || result.equals(Result.BlackJack)) {
            return Result.LOSE;
        }
        if (result.equals(Result.LOSE)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public String getPrintFormat() {
        return printFormat;
    }

}
