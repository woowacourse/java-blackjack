package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Cards;
import java.util.List;

public class User {

    private final UserName name;
    private final Cards cards;

    public User(String name) {
        this.name = new UserName(name);
        this.cards = new Cards();
    }

    public void bring(Card card) {
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
        return calculateCardsValue() > 21;
    }

    private boolean isBlackjack() {
        return calculateCardsValue() == 21;
    }

    public boolean isFinished() {
        return isBurst() || isBlackjack();
    }
}
