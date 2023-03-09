package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 17;

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public List<Card> initialHand() {
        return hand.getCards()
                .stream()
                .limit(1)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean isDrawable() {
        return UPPER_BOUND_OF_DRAWABLE_SCORE > score();
    }
}
