package blackjack.domain.handrank;

public abstract class HankRank {

    public abstract SingleMatchResult competeWithPlayer(HankRank playerHandRank);

    protected abstract int getScore();

    protected abstract boolean isBlackjack();

    protected abstract boolean isBust();
}
