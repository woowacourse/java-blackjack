package domain.card;

import java.util.List;

public class DealerCards extends Cards {

    private static final int MIN_SCORE = 16;

    public DealerCards(List<Card> cards) {
        super(cards);
    }


    public String getFirstCard() {
        return cards.get(0).asString();
    }

    @Override
    public String toString() {
        return "DealerCards{" +
                "cards=" + cards +
                '}';
    }

    @Override
    public boolean canDraw() {
        return bestSum() <= MIN_SCORE;
    }
}
