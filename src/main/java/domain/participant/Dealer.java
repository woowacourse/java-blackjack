package domain.participant;

import domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 17;
    private static final int NUMBER_OF_INITIAL_OPEN_CARD = 1;

    public Dealer() {
        super(new Name("딜러"), new Hand());
    }

    @Override
    public List<Card> initialHand() {
        return hand.getCards().stream()
                .limit(NUMBER_OF_INITIAL_OPEN_CARD)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean isDrawable() {
        return UPPER_BOUND_OF_DRAWABLE_SCORE > score();
    }
}
