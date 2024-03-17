package blackjack.domain.handrank;

public abstract class HandRank {

    public abstract SingleMatchResult matchWithPlayer(HandRank playerHandRank);

    protected abstract int getScore();

    protected abstract boolean isBlackjack();

    protected abstract boolean isBust();
}
