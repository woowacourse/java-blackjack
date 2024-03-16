package domain.participant;

import domain.blackjack.Deck;
import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_HIT_COUNT = 16;
    public static final String DEALER_NAME = "딜러";

    private final Deck deck;

    public Dealer() {
        super(new Name(DEALER_NAME));
        deck = new Deck();
    }

    @Override
    public boolean canHit() {
        return hands.calculateScore() <= DEALER_HIT_COUNT;
    }

    @Override
    public List<Card> revealCardOnInitDeal() {
        return hands.getValue(1);
    }

    public Card draw() {
        return deck.draw();
    }

    public List<Card> draw(int drawCount) {
        return deck.draw(drawCount);
    }
}
