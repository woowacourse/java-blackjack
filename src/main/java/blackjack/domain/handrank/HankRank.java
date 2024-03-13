package blackjack.domain.handrank;

public interface HankRank {

    SingleMatchResult matchAtDealer(HankRank playerHandRank);

    int getScore();

    boolean isBlackjack();

    boolean isBust();
}
