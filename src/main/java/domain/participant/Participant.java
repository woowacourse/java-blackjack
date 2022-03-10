package domain.participant;

import static java.lang.Integer.compare;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ExceptionMessages;

public class Participant implements Comparable<Participant> {

    public static final int MAX_SCORE = 21;
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

    public void hitInitialTurn() {
        cards.addCards(Deck.handOutInitialTurn());
    }

    public int calculateResult() {
        return cards.calculateResult();
    }

    public String getName() {
        return name.getValue();
    }

    private boolean isOverMaxScore() {
        return calculateResult() > MAX_SCORE;
    }

    @Override
    public int compareTo(Participant o) {
        if (o.isOverMaxScore()) {
            return 1;
        }
        if (isOverMaxScore()) {
            return -1;
        }

        return compare(calculateResult(), o.calculateResult());
    }
}
