package blackjack.strategy;

@FunctionalInterface
public interface HitOrStayChoiceStrategy  {

    boolean shouldHit();
}
