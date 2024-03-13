package blackjack.domain.handrank;

public interface HankRank {

    SingleMatchResult matchAtDealer(HankRank playerHandRank);

    boolean isBlackjack();

    boolean isBust();
}
