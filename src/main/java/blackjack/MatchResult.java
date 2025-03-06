package blackjack;

public enum MatchResult {
    LOSE, WIN, DRAW;

    public static MatchResult judge(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (isBothBlackjack(player, dealer)) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return WIN;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }
        return determineByCompareTotal(player.getTotal(), dealer.getTotal());
    }

    private static boolean isBothBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    private static MatchResult determineByCompareTotal(int playerTotal, int dealerTotal) {
        if (playerTotal > dealerTotal) {
            return WIN;
        }
        if (playerTotal < dealerTotal) {
            return LOSE;
        }
        return DRAW;
    }
}
