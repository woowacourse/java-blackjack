package blackjack.model;

@FunctionalInterface
public interface NumberGenerator {
    int pick(int bound);
}
