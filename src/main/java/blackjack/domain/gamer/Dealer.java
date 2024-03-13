package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer {
    public static final int HIT_UPPER_BOUND = 16;
    private static final int FIRST_DEAL_CARD_INDEX = 0;

    private final Gamer gamer;

    public Dealer(Gamer gamer) {
        this.gamer = gamer;
    }

    public boolean isScoreUnderBound() {
        return gamer.hand.calculateScore() <= HIT_UPPER_BOUND;
    }

    public void draw(Card card) {
        gamer.draw(card);
    }

    public void draw(List<Card> cards) {
        gamer.draw(cards);
    }

    public boolean isBust() {
        return gamer.isBust();
    }

    public boolean isBlackjack() {
        return gamer.isBlackjack();
    }


    public List<Card> getCards() {
        if (gamer.getCards().size() == 2) {
            return List.of(gamer.hand.getMyCardAt(FIRST_DEAL_CARD_INDEX));
        }
        return gamer.hand.getMyCards();
    }

    public int getScore() {
        return gamer.getScore();
    }
}
