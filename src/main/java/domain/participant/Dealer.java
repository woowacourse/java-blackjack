package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Dealer extends Participant {

    private static final int STAND_SCORE = 17;

    public Dealer(Hand hand) {
        super(new Name("딜러"), hand);
    }

    @Override
    public List<String> getInitialCards() {
        List<String> cards = hand.getCards().stream()
                .map(Card::getCardName)
                .toList();
        return List.of(cards.getFirst());
    }

    @Override
    public void playTurn(Deck deck) {
        super.playTurn(deck);
        if (isStand()) {
            changeState();
        }
    }

    private boolean isStand() {
        return getScore() >= STAND_SCORE;
    }
}
