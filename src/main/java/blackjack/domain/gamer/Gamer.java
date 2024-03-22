package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.PlayerResult;
import blackjack.domain.money.Chip;

import java.util.List;

public class Gamer {
    private final Hand hand;
    private final Chip chip;

    public Gamer(Hand hand, Chip chip) {
        this.hand = hand;
        this.chip = chip;
    }

    public void draw(List<Card> cards) {
        hand.add(cards);
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isMaxScore() {
        return hand.isMaxScore();
    }

    public double madeProfit(PlayerResult playerResult) {
        return chip.totalProfit(playerResult);
    }

    public int score() {
        return hand.score().toInt();
    }

    public Card cardAt(int index) {
        return hand.myCardAt(index);
    }

    public List<Card> cards() {
        return hand.myCards();
    }

    public Long profit() {
        return chip.value();
    }
}
