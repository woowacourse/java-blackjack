package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ExceptionMessages;

public class Participant {
    protected final Cards cards;
    protected final Name name;

    public Participant(String name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = new Name(name);
    }

    public boolean canDrawCard() {
        return true;
    }

    public void hit(Card card) {
        if (!canDrawCard()) {
            throw new IllegalStateException(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
        }
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
