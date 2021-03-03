package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer {
    private static final int NO_MORE_DRAW_NUMBER = 16;
    private final Hand hand;
    private Status status;

    public Dealer() {
        this.hand = Hand.createEmptyHand();
    }

    public void firstDraw(Card first, Card second) {
        drawCard(first);
        drawCard(second);
    }

    public void drawCard(Card card) {
        hand.add(card);
        changeStatus();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int handSize() {
        return hand.size();
    }

    public boolean isSameStatus(Status status){
        return this.status == status;
    }

    public void changeStatus(){
        status = Status.of(hand.calculateScore());
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isUnderSixteen() {
        return hand.calculateScore() < NO_MORE_DRAW_NUMBER;
    }

    public int calculateScore() {
        return hand.calculateScore();
    }
}
