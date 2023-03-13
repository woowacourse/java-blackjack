package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;

import java.util.List;

public class Participant {

    private final String name;
    private final Cards cards;

    public Participant(final String name) {
        this.name = name.strip();
        this.cards = new Cards();
    }

    public void drawCard(final CardPack cardPack) {
        cards.add(cardPack.takeOne());
    }

    public List<Card> showCards() {
        return cards.getCards();
    }

    public Score getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return cards.isBust();
    }
}
