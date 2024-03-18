package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

final class Stand extends HandRank {

    public Stand(Hand hand) {
        super(validate(hand));
    }

    private static Hand validate(Hand hand) {
        if (hand.isBlackjack() || hand.isBusted()) {
            throw new IllegalArgumentException("스탠드는 블랙잭 또는 버스트가 아이어야 합니다.");
        }
        return hand;
    }

    @Override
    public SingleMatchResult matchWithPlayer(HandRank playerHandRank) {
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.PLAYER_BLACKJACK;
        }
        if (playerHandRank.isBust()) {
            return SingleMatchResult.DEALER_WIN;
        }
        return matchThroughScore(playerHandRank);
    }

    private SingleMatchResult matchThroughScore(HandRank playerHandRank) {
        if (playerHandRank.getScore() > this.getScore()) {
            return SingleMatchResult.PLAYER_WIN;
        }
        if (playerHandRank.getScore() == this.getScore()) {
            return SingleMatchResult.DRAW;
        }
        return SingleMatchResult.DEALER_WIN;
    }
}
