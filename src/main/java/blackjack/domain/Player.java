package blackjack.domain;

public class Player extends Participant {

    private static final int BLACKJACK_POINT = 21;

    public Player(String name) {
        super(name, new Hand());

    }

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean canHit() {
        return getTotalPoint() < BLACKJACK_POINT;
    }

    public GameResult compareResult(Dealer dealer) {
        if (this.isBust()) {
            return GameResult.LOSE;
        }

        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        if (isBlackJack()) {
            return judgeBlackJackResult(dealer);
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
}
