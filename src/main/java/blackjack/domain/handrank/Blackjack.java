package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

final class Blackjack extends HandRank {

    public Blackjack(Hand hand) {
        super(hand);
    }

    private static Hand validate(Hand hand) {
        if (!hand.isBlackjack()) {
            throw new IllegalArgumentException("해당 핸드는 블랙잭이어야 합니다.");
        }
        return hand;
    }

    @Override
    public SingleMatchResult matchWithPlayer(HandRank playerHandRank) {
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.DRAW;
        }
        return SingleMatchResult.DEALER_WIN;
    }
}
