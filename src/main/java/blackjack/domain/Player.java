package blackjack.domain;

public class Player extends Participant {

    private static final int BLACKJACK_POINT = 21;
    private BettingAmount bettingAmount;

    public Player(String name) {
        super(name, new Hand());
    }

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    public Player(String name, BettingAmount bettingAmount) {
        super(name, new Hand());
        this.bettingAmount = bettingAmount;
    }

    @Override
    public boolean canHit() {
        return getTotalPoint() < BLACKJACK_POINT;
    }

    private GameResult compareResult(Dealer dealer) {
        if (this.isBust()) {
            return GameResult.LOSE;
        }

        if (isBlackJack()) {
            return judgeBlackJackResult(dealer);
        }

        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        return judgeNearestBlackJackPoint(dealer);
    }

    private GameResult judgeNearestBlackJackPoint(Dealer dealer) {
        int playerTotalPoint = getTotalPoint();
        int dealerTotalPoint = dealer.getTotalPoint();

        if (playerTotalPoint > dealerTotalPoint) {
            return GameResult.WIN;
        }

        if (playerTotalPoint < dealerTotalPoint) {
            return GameResult.LOSE;
        }
        return GameResult.TIE;
    }

    private GameResult judgeBlackJackResult(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return GameResult.TIE;
        }
        return GameResult.BLACKJACK_WIN;
    }

    public long calculateEarningAmount(Dealer dealer) {
        GameResult gameResult = compareResult(dealer);
        return bettingAmount.calculateEarningAmount(gameResult);
    }
}
