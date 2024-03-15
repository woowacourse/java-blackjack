package blackjack.domain.gamer;

import blackjack.domain.supplies.Card;
import blackjack.domain.supplies.Chip;
import blackjack.domain.supplies.Hand;

import java.util.List;

public class Dealer {
    public static final int HIT_UPPER_BOUND = 16;
    private static final int PUBLIC_DEAL_CARD_INDEX = 0;

    private final Gamer gamer;

    public Dealer(Chip chip) {
        this.gamer = new Gamer(new Hand(), chip);
    }

    public boolean isScoreUnderBound() {
        return gamer.getScore() <= HIT_UPPER_BOUND;
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

    public Card findPublicDealCard() {
        return gamer.getCardAt(PUBLIC_DEAL_CARD_INDEX);
    }

    public List<Card> getCards() {
        return gamer.getCards();
    }

    public int getScore() {
        return gamer.getScore();
    }
}
