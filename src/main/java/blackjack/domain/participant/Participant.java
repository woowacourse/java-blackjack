package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Cards;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_NUMBER = 21;

    private final UserName name;
    private final Cards cards;

    public Participant(String name) {
        this.name = new UserName(name);
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name.name();
    }

    public String getFirstCardName() {
        return cards.getFirstName();
    }

    public List<String> getCardsName() {
        return cards.getNames();
    }

    public int calculateCardsValue() {
        return cards.calculateValue();
    }

    public boolean isBurst() {
        return calculateCardsValue() > BLACKJACK_NUMBER;
    }

    public boolean isBlackjack() {
        return calculateCardsValue() == BLACKJACK_NUMBER && cards.getCardsSize() == 2;
    }

    public boolean isFinished() {
        return isBurst() || isBlackjack();
    }
}
