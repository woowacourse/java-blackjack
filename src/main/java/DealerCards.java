import java.util.List;

public class DealerCards extends GamerCards {

    private static final int DEALER_HIT_THRESHOLD = 16;

    public DealerCards(List<Card> cards) {
        super(cards);
    }

    public boolean hasScoreUnderHitThreshold() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }
}
