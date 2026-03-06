public class Game {

    private final CardDistributor cardDistributor;

    public Game(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public void dealerDrawsCardsUntilDone(Dealer dealer) {
        while (!dealer.isDealerDone()) {
            cardDistributor.distributeCardToDealer(dealer);
        }
    }

    public ScoreCompareResult compareScore(Player player, Dealer dealer) {
        boolean isPlayerBust = player.isBust();
        boolean isDealerBust = dealer.isBust();

        if (isPlayerBust || isDealerBust) {
            return compareScoreWhenBust(isPlayerBust, isDealerBust);
        }

        return compareScoreWhenNotBust(player.calculateTotalScore(), dealer.calculateTotalScore());

    }

    private ScoreCompareResult compareScoreWhenBust(boolean isPlayerBust, boolean isDealerBust) {
        if (isPlayerBust && isDealerBust) {
            return ScoreCompareResult.PUSH;
        }
        if (isPlayerBust) {
            return ScoreCompareResult.DEALER_WIN;
        }
        if (isDealerBust) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        throw new IllegalArgumentException("버스트인 사람이 1명은 포함되어야 합니다.");
    }

    private ScoreCompareResult compareScoreWhenNotBust(int playerTotalScore, int dealerTotalScore) {
        if (playerTotalScore > dealerTotalScore) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        if (playerTotalScore < dealerTotalScore) {
            return ScoreCompareResult.DEALER_WIN;
        }
        return ScoreCompareResult.PUSH;
    }
}
