package domain.participant;

import static domain.result.GameResult.BLACKJACK_SCORE;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Participant {

    private static final int INIT_CARD_SIZE = 2;

    private final Name name;
    private final Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return getScore() == BLACKJACK_SCORE && getCards().size() == INIT_CARD_SIZE;
    }

    public void playTurn(Deck deck) {
        Card hitCard = deck.drawCard();
        hand.receiveCard(hitCard);
    }

    public void initHand(Deck deck) {
        for (int size = 0; size < INIT_CARD_SIZE; size++) {
            Card card = deck.drawCard();
            hand.receiveCard(card);
        }
    }

    public String getName() {
        return name.value();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculate();
    }

}
