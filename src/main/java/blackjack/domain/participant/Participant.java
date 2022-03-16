package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Objects;

public abstract class Participant {

    protected final Name name;
    protected Cards cards;

    public Participant(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public abstract boolean isFinished();

    public void drawCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name.getValue();
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
