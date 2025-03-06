package blackjack.domain;

public class WinnerDecider {

    private final Dealer dealer;

    public WinnerDecider(Dealer dealer) {
        this.dealer = dealer;
    }

    public WinningResult decidePlayerWinning(Player player) {
        int dealerScore = dealer.calculateMaxScore();
        int playerScore = player.calculateMaxScore();
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return WinningResult.LOSE;
        }

        if (dealerScore > 21) {
            return WinningResult.WIN;
        }

        if (dealerScore == playerScore) {
            return WinningResult.DRAW;
        }
        if (dealerScore > playerScore) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }

    public WinningResult decideDealerWinning(Player player) {
        int dealerScore = dealer.calculateMaxScore();
        int playerScore = player.calculateMaxScore();
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return WinningResult.WIN;
        }

        if (dealerScore > 21) {
            return WinningResult.LOSE;
        }

        if (dealerScore == playerScore) {
            return WinningResult.DRAW;
        }
        if (dealerScore > playerScore) {
            return WinningResult.WIN;
        }
        return WinningResult.LOSE;
    }


}
