package blackJack.domain.Result;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    BlackJack((dealer, player) -> !dealer.isBlackJack() && player.isBlackJack(), 1.5),
    LOSE((dealer, player) -> player.isBurst() || !dealer.isBurst() && dealer.getScore() > player.getScore(), -1),
    WIN((dealer, player) -> dealer.isBurst() || !player.isBurst() && dealer.getScore() < player.getScore(), 1),
    DRAW((dealer, player) -> !player.isBurst() && dealer.getScore() == player.getScore(), 0);

    private final BiPredicate<Dealer, Player> predicate;
    private final double profit;

    Result(BiPredicate<Dealer, Player> predicate, double profit) {
        this.predicate = predicate;
        this.profit = profit;
    }

    public static Result judge(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(dealer, player))
                .findFirst()
                .get();
    }

    public int calculateBenefit(Player player) {
        return (int) (player.getBettingMoney() * profit);
    }
}
