package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.common.Name;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player {
    private static final int BUST_SIZE = 21;
    protected final Name name;
    protected final Cards cards;

    public Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public int calculateScore() {
        int sum = cards.sum();
        if (cards.containAce() && sum <= 11) {
            return sum + 10;
        }
        return sum;
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name.asString();
    }

    public List<Card> getCards() {
        return cards.toList();
    }

    public boolean isBust() {
        return cards.sum() > BUST_SIZE;
    }
}
