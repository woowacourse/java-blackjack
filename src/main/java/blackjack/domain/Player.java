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
        if (getTotalPoint() < BLACKJACK_POINT) {
            return true;
        }
        return false;
    }

    public String getName() {
        return super.getName();
    }

    public GameResult compareResult(Dealer dealer) {
        if (this.isBust()) {
            return GameResult.LOSE;
        }

        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        return judgeNearestBlackJackPoint(this, dealer);
    }

    private GameResult judgeNearestBlackJackPoint(Player player, Dealer dealer) {
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
}
