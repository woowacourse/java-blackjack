package domain.board;

import domain.PlayerStatus;
import domain.user.Dealer;

public class DealerBoard {

    private static final int DEALER_THRESHOLDS = 16;
    private static final int BLACK_JACK_POINT = 21;
    private final Dealer dealer;
    private PlayerStatus status;

    public DealerBoard(Dealer dealer, PlayerStatus status) {
        this.dealer = dealer;
        this.status = status;
    }

    public void update() {
        int point = this.dealer.getPoint();
        status = getResultByScore(point);
    }

    protected PlayerStatus getResultByScore(int point) {
        if (point > BLACK_JACK_POINT) {
            return PlayerStatus.BUST;
        }
        if (point == BLACK_JACK_POINT) {
            return PlayerStatus.BLACK_JACK;
        }
        if (point > DEALER_THRESHOLDS) {
            return PlayerStatus.STAND;
        }
        return PlayerStatus.HIT_ABLE;
    }

    public boolean needMoreCard() {
        return status == PlayerStatus.HIT_ABLE;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public int getPoint() {
        return dealer.getPoint();
    }
}
