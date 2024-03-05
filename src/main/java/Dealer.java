import java.util.Collections;
import java.util.List;

public class Dealer {

    private final List<Card> cards;
    private final Score score;

    public Dealer(List<Card> cards) {
        this.cards = cards;
        this.score = new Score(0);
        cards.forEach(score::addScore);
    }

    public void hit(Card card) {
        cards.add(card);
        score.addScore(card);
    }

    public boolean isNotBust() {
        return score.isLowerThanBustThreshold();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean canHit() {
        return score.isLowerThanDealerThreshold();
    }
}
