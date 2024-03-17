package participant.dealer;

import card.Card;
import card.CardDeck;
import card.Hand;
import java.util.List;

public class Dealer {

    private static final int MIN_DEALER_SCORE = 16;

    private final Hand hand;

    public Dealer(List<Card> initCards) {
        this.hand = new Hand(initCards);
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public void playGame(CardDeck cardDeck) {
        while (isNotOverMinScore()) {
            hand.addCard(cardDeck.pickCard());
        }
    }

    private boolean isNotOverMinScore() {
        return hand.countMaxScore() <= MIN_DEALER_SCORE;
    }

    public Hand getCards() {
        return hand;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int getCardScore() {
        return hand.countMaxScore();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
}
