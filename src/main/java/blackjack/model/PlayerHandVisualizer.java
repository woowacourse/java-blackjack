package blackjack.model;

@FunctionalInterface
public interface PlayerHandVisualizer {

    void accept(Player player);
}
