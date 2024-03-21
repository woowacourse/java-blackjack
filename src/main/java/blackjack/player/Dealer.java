package blackjack.player;

import blackjack.card.Card;
import java.util.List;

public class Dealer extends Player {

    private static final int MAX_DRAWABLE_SCORE = 16;

    public Dealer() {
        super("딜러");
    }

    public Dealer(Hand hand) {
        super("딜러", hand);
    }

    public Card getFirstCard() {
        List<Card> cards = getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("딜러가 카드를 가지고 있지 않습니다.");
        }
        return cards.get(0);
    }

    @Override
    public boolean hasDrawableScore() {
        return getScore().isSmallerThanOrEqualTo(new Score(MAX_DRAWABLE_SCORE));
    }
}
