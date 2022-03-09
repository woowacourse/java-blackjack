package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ExceptionMessages;

public class Player {

    public static final int MAX_CARD_SUM = 21;

    private final Name name;
    private final Cards cards;

    public Player(String name) {
        this.name = new Name(name);
        this.cards = new Cards(new ArrayList<>());
    }

    public boolean canDrawCard() {
        return cards.calculateSum() < MAX_CARD_SUM;
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
