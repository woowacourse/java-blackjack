package blackjack.model;

import java.util.List;

public class Dealer extends Participant {

    public Dealer(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public List<Card> getVisibleCards() {
        return List.of(getCards().getFirst());
    }

    public boolean shouldHit(DealerHitPolicy dealerHitPolicy, ScoreCalculator scoreCalculator) {
        return dealerHitPolicy.shouldHit(scoreCalculator.calculate(getCards()));
    }
}
