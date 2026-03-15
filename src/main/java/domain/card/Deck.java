package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final Cards cards;

    public Deck() {
        this.cards = createDeck();
    }

    public Help drawCard() {
        validateDeckSize();
        Help help = cards.removeFirst();
        return help;
    }

    private void validateDeckSize() {
        if (cards.getSize() == 0) {
            throw new IllegalArgumentException("덱에 카드가 없습니다.");
        }
    }

    private Cards createDeck() {
        List<Help> helps = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                helps.add(new Help(shape, number));
            }
        }
        Collections.shuffle(helps);
        return new Cards(helps);
    }
}
