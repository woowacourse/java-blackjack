package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;
import blackjack.domain.money.Chip;

import java.util.List;

public class Dealer {
    public static final int HIT_UPPER_BOUND = 16;
    private static final int PUBLIC_DEAL_CARD_INDEX = 0;

    private final Gamer gamer;

    public Dealer(Chip chip) {
        this.gamer = new Gamer(new Hand(), chip);
    }

    public boolean isScoreUnderBound() {
        return !gamer.score().isBiggerThan(new Score(HIT_UPPER_BOUND));
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

    public Double calculateProfit(Double playerProfit) {
        return playerProfit * -1;
    }

    public Card findPublicDealCard() {
        return gamer.cardAt(PUBLIC_DEAL_CARD_INDEX);
    }

    public List<Card> cards() {
        return gamer.cards();
    }

    public Score score() {
        return gamer.score();
    }
}
