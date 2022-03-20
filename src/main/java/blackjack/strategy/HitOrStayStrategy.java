package blackjack.strategy;

@FunctionalInterface
public interface HitOrStayStrategy {

    boolean shouldHit();
}
