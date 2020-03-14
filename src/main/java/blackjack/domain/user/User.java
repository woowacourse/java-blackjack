package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Objects;

public abstract class User {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    private Name name;
    private Cards cards;

    public User(String name) {
        this.name = new Name(name);
        cards = new Cards();
    }

    public String getName() {
        return name.getName();
    }

    public void addCard(Card card) {
        Objects.requireNonNull(card, String.format(NULL_ERR_MSG, "카드"));
        cards.add(card);
    }

    public String showCards() {
        return String.join(" ,", cards.getMessage());
    }

    public Cards getCards() {
        return cards;
    }

    public int computeSum() {
        return cards.computeSum();
    }

    public abstract boolean receivable();
}
