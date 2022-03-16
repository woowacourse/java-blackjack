package blackjack.domain.participant;

public class Profit {

    private static final int COUNT_FOR_BLACKJACK = 2;
    private static final double BLACKJACK_WIN_RATE = 1.5;
    private static final double WIN_RATE = 1;
    private static final double LOSE_RATE = -1;

    private final double value;

    private Profit(double value) {
        this.value = value;
    }

    public static Profit of(Player player, Dealer dealer) {
        if (player.isBlackjack(COUNT_FOR_BLACKJACK) && !dealer.isBlackjack(COUNT_FOR_BLACKJACK)) {
            return new Profit(multiply(player, BLACKJACK_WIN_RATE));
        }
        if (player.isWin(dealer.getScore())) {
            return new Profit(multiply(player, WIN_RATE));
        }
        return new Profit(multiply(player, LOSE_RATE));
    }

    public double getValue() {
        return value;
    }

    private static double multiply(Player player, double num) {
        return player.getBettingMoney() * num;
    }
}
