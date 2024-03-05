package blackjack.model.generator;

@FunctionalInterface
public interface IndexGenerator {
    int generate(int maxRange);
}
