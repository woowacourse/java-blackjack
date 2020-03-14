package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public abstract class User {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    private Name name;
    private Point point;
    private List<Card> cards;

    public User(String name) {
        this.name = new Name(name);
        this.point = new Point();
        this.cards = new ArrayList<>();
    }

    public Name getName() {
        return name;
    }

    public void addCard(Card card) {
        Objects.requireNonNull(card, String.format(NULL_ERR_MSG, "카드"));
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
    public Point getPoint() {
        return point;
    }

    public abstract boolean receivable();
}
