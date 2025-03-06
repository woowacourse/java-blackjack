package blackjack.model;

@FunctionalInterface
public interface PlayerAction {

    void accept(Player player);
}
