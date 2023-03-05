package domain.participant;

import static java.util.stream.Collectors.toList;

import domain.card.Card;
import domain.card.DrawnCards;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_DEFAULT_NAME = "딜러";
    private static final int DRAW_LIMIT_SCORE = 16;

    public Dealer(final DrawnCards drawnCards) {
        super(new Name(DEALER_DEFAULT_NAME), drawnCards);
    }

    @Override
    public List<Card> openDrawnCards() {
        List<Card> openCards = drawnCards.stream()
                .skip(1)
                .collect(toList());

        return Collections.unmodifiableList(openCards);
    }

    @Override
    public boolean isDrawable() {
        return calculateScore() <= DRAW_LIMIT_SCORE;
    }
}
