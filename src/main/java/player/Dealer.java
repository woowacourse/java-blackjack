package player;

import card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int MAX_DRAWABLE_SCORE = 16;

    public Dealer() {
        super("딜러");
    }

    Dealer(Hand hand) {
        super("딜러", hand);
    }

    @Override
    public boolean hasDrawableScore() {
        return hand.calculateScore() <= MAX_DRAWABLE_SCORE;
    }

    public Card getFirstCard() {
        List<Card> cards = super.getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 딜러가 카드를 가지고 있지 않습니다.");
        }
        return cards.get(0);
    }
}
