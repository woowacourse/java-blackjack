package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.hands.Hands;
import blackjack.domain.hands.HandsScore;

import java.util.List;

public class Dealer {
    private static final HandsScore DEALER_DRAW_THRESHOLD = HandsScore.from(16);

    private final Hands dealerHands;

    public Dealer() {
        this.dealerHands = new Hands();
    }

    public static int getDrawThresHoldScore() {
        return DEALER_DRAW_THRESHOLD.toInt();
    }

    public boolean shouldDraw() {
        HandsScore handsScore = dealerHands.getHandsScore();
        return handsScore.isSame(DEALER_DRAW_THRESHOLD)
                || handsScore.isLowerThan(DEALER_DRAW_THRESHOLD);
    }

    public void addCard(Card card) {
        dealerHands.addCard(card);
    }

    public boolean isBust() {
        return dealerHands.isBust();
    }

    public boolean isBlackJack() {
        return dealerHands.isBlackJack();
    }

    public boolean hasHigherScore(Player player) {
        return dealerHands.getHandsScore()
                .isHigherThan(player.getHandsScore());
    }

    public boolean hasSameScore(Player player) {
        return dealerHands.getHandsScore()
                .isSame(player.getHandsScore());
    }

    public boolean hasLowerScore(Player player) {
        return dealerHands.getHandsScore()
                .isLowerThan(player.getHandsScore());
    }

    public Card getFirstCard() {
        List<Card> cards = dealerHands.getHands();
        if (cards.isEmpty()) {
            throw new IllegalStateException("아직 지니고 있는 카드가 없습니다");
        }

        return cards.get(0);
    }

    public List<Card> getHandsCards() {
        return dealerHands.getHands();
    }

    public HandsScore getHandsScore() {
        return dealerHands.getHandsScore();
    }
}
