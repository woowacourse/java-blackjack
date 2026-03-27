package domain.participant;

import domain.GameResult;
import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards;

    public Participant(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public String getName() {
        return name.toString();
    }

    public List<String> getCardNames() {
        return cards.getCardNames();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getTotalSum() {
        return cards.getTotalSum();
    }

    public boolean isBust() {
        return getTotalSum() > GameResult.BLACKJACK_MAX_NUMBER;
    }

    public boolean isBlackjack() {
        return cards.isSize(2) && getTotalSum() == GameResult.BLACKJACK_MAX_NUMBER;
    }
}
