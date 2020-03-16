package blackjack.domain.user;


import blackjack.domain.GameRule;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements GameRule {
    protected static int INITIAL_CARD_SIZE = 2;
    protected static int ADDITIONAL_CARD_SIZE = 1;

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

    abstract public void drawCard(CardDeck deck);

    public List<Card> getCards() {
        return cards;
    }

    public Point getPoint() {
        return point;
    }
}
