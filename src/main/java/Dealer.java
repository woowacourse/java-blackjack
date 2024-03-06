import java.util.ArrayList;

public class Dealer {

    private final DealerCards cards;

    public Dealer() {
        this.cards = new DealerCards(new ArrayList<>());
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public boolean isNotBust() {
        return cards.hasScoreUnderBustThreshold();
    }

    public boolean canHit() {
        return cards.hasScoreUnderHitThreshold();
    }

    public DealerCards getCards() {
        return cards;
    }
}
