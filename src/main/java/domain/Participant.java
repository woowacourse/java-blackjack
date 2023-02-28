package domain;

import java.util.List;

public class Participant implements Player {

    private final Name name;
    private final Cards cards;

    private Participant(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Participant from(final Name name) {
        return new Participant(name, new Cards());
    }

    @Override
    public List<Card> displayCards() {
        return cards.displayCards();
    }

    public void takeCard(final Card card) {
        cards.takeCard(card);
    }
}
