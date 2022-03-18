package blackJack.domain;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    BlackJack("승", (dealer, player) -> !dealer.isBlackJack() && player.isBlackJack(), 1.5),
    LOSE("패", (dealer, player) -> player.isBurst() || !dealer.isBurst() && dealer.getScore() > player.getScore(), -1),
    WIN("승", (dealer, player) -> dealer.isBurst() || !player.isBurst() && dealer.getScore() < player.getScore(), 1),
    DRAW("무", (dealer, player) -> !player.isBurst() && dealer.getScore() == player.getScore(), 0);

    private final String printFormat;
    private final BiPredicate<Dealer, Player> predicate;
    private final double profit;

    Result(String value, BiPredicate<Dealer, Player> predicate, double profit) {
        this.printFormat = value;
        this.predicate = predicate;
        this.profit = profit;
    }

    public static Result judge(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(dealer, player))
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

    public double getProfit() {
        return profit;
    }

    public int calculateBenefit(Player player) {
        return (int) (player.getBettingMoney() * profit);
    }
}
