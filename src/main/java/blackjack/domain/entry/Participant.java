package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.entry.vo.Name;
import java.util.List;

public abstract class Participant {

    private final Name name;

    protected Participant(Name name) {
        this.name = name;
    }

    public abstract int countScore();

    public abstract boolean isDealer();

    public abstract List<Card> getCards();

    public final Name getName() {
        return this.name;
    }

}
