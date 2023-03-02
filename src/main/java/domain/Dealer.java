package domain;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    public Dealer(final Name name, final DrawnCards drawnCards) {
        super(name, drawnCards);
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
