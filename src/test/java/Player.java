import blackjack.domain.Card;
import blackjack.domain.CardPack;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> cards;

    public Player(final CardPack cardPack) {
        cards = new ArrayList<>();

        cards.add(cardPack.getDeal());
        cards.add(cardPack.getDeal());
    }

    public List<Card> getCards() {
        return cards;
    }
}
