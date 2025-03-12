package blackjack.model.participant;

@FunctionalInterface
public interface PlayerHandVisualizer {

    void accept(Player player);
}
