package blackjack.domain.handrank;

public interface HankRank {

    SingleMatchResult competeWithPlayer(HankRank playerHandRank);

    int getScore();

    boolean isBlackjack();

    boolean isBust();
}
