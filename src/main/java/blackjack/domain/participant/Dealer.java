package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hands.HandsScore;

import java.util.List;

public class Dealer extends Participant {
    private static final HandsScore DEALER_DRAW_THRESHOLD = HandsScore.from(16);

    public Dealer() {
        super();
    }

    public static int getDrawThresHoldScore() {
        return DEALER_DRAW_THRESHOLD.toInt();
    }

    @Override
    public boolean canAddCard() {
        HandsScore handsScore = super.getHandsScore();
        return handsScore.isSame(DEALER_DRAW_THRESHOLD)
                || handsScore.isLowerThan(DEALER_DRAW_THRESHOLD);
    }

    public boolean hasHigherScore(Player player) {
        return super.getHandsScore()
                .isHigherThan(player.getHandsScore());
    }

    public boolean hasSameScore(Player player) {
        return super.getHandsScore()
                .isSame(player.getHandsScore());
    }

    public boolean hasLowerScore(Player player) {
        return super.getHandsScore()
                .isLowerThan(player.getHandsScore());
    }

    public Card getFirstCard() {
        List<Card> cards = super.getHandsCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("아직 지니고 있는 카드가 없습니다");
        }
        return cards.get(0);
    }
}
