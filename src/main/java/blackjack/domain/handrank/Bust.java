package blackjack.domain.handrank;

import blackjack.domain.card.Hand;

final class Bust extends HandRank {

    public Bust(Hand hand) {
        super(validate(hand));
    }

    private static Hand validate(Hand hand) {
        if (!hand.isBusted()) {
            throw new IllegalArgumentException("해당 핸드는 버스트이어야 합니다.");
        }
        return hand;
    }

    @Override
    public SingleMatchResult matchWithPlayer(HandRank playerHandRank) {
        if (playerHandRank.isBust()) {
            return SingleMatchResult.DEALER_WIN;
        }
        if (playerHandRank.isBlackjack()) {
            return SingleMatchResult.PLAYER_BLACKJACK;
        }
        return SingleMatchResult.PLAYER_WIN;
    }
}
