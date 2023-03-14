package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.cardpack.CardPack;

import java.util.List;

public class Participant {

    private final String name;
    private final Hand hand;

    public Participant(final String name) {
        this.name = name.strip();
        this.hand = new Hand();
    }

    public void drawCard(final CardPack cardPack) {
        hand.add(cardPack.takeOne());
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public Score getScore() {
        return hand.getScore();
    }

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
