package domain;

import java.util.List;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/08
 */
public abstract class Participant {

    private final Name name;
    protected final Cards cards;

    protected Participant(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public abstract List<String> printInitCards();

    public boolean addCard(final Card card) {
        return cards.addCard(card);
    }

    public int sumOfCards() {
        return cards.sumOfCards();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isNotBurst() {
        return cards.isNotBurst();
    }
}
