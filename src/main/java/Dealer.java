import java.util.ArrayList;

public class Dealer {

    private final Cards cards;
    private final Score score;

    public Dealer() {
        this.cards = new Cards(new ArrayList<>());
        this.score = new Score(0);
    }

    public void hit(Card card) {
        cards.addCard(card);
        score.addScore(card);
    }

    public boolean isNotBust() {
        return score.isLowerThanBustThreshold();
    }

    public boolean canHit() {
        return score.isLowerThanDealerThreshold();
    }

    public Cards getCards() {
        return cards;
    }
}
