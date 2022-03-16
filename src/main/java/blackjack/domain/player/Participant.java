package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Participant {

    protected static final int MAX_BLACKJACK_SCORE = 21;

    private final String name;
    protected final Cards cards;

    public Participant(String name, Cards cards) {
        checkEmptyName(name);
        this.name = name;
        this.cards = cards;
    }

    private void checkEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public List<Card> openAllOfCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public abstract List<Card> openFirstCards();

    public abstract boolean isBust();
}
