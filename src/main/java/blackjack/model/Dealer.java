package blackjack.model;

import java.util.List;

public class Dealer extends Participant {

    public Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public Dealer(String name, Hand hand) {
        super(new Name(name), hand);
    }

    @Override
    public List<Card> getVisibleCards() {
        return List.of(getCards().getFirst());
    }

    public boolean shouldHit(DealerHitPolicy dealerHitPolicy, Score score) {
        return dealerHitPolicy.shouldHit(score);
    }
}
