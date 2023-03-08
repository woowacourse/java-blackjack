package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;

import java.util.List;

public class Participant {

    private final String name;
    private final Cards cards;

    public Participant(final String name) {
        this.name = name.trim();
        this.cards = new Cards();
    }

    public void drawCard(final CardPack cardPack) {
        cards.add(cardPack.takeOne());
    }

    public List<Card> showCards() {
        return cards.getCards();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public String getName() {
        return name;
    }
}
