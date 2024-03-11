package blackjack.model.deck;

@FunctionalInterface
public interface IndexGenerator {
    int generate(int maxRange);
}
