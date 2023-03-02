package domain;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_DEFAULT_NAME = "딜러";

    public Dealer(final DrawnCards drawnCards) {
        super(new Name(DEALER_DEFAULT_NAME), drawnCards);
    }

    @Override
    public List<Card> openDrawnCards() {
        List<Card> openCards = drawnCards.getCards()
                .stream()
                .skip(1)
                .collect(toList());

        return Collections.unmodifiableList(openCards);
    }
}
