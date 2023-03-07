package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;

import java.util.List;

public class Participant {

    private final String name;
    private final Hand hand;

    public Participant(final String name) {
        this.name = name.trim();
        this.hand = new Hand();
    }

    public void drawCard(final CardPack cardPack) {
        hand.add(cardPack.takeOne());
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public String getName() {
        return name;
    }
}
