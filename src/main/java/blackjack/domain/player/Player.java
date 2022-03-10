package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Player {

    private final String name;
    protected final Cards cards;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
    }

    public void pickCard(Card card) {
        cards.addCard(card);
    }

    public abstract boolean isPossibleToPickCard();

    public String getName() {
        return name;
    }
}
