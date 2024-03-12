package blackjack.model.generator;

@FunctionalInterface
public interface IndexGenerator {
    int generate(final int maxRange);
}
