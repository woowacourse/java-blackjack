package blackjack;

@FunctionalInterface
public interface PlayerAction {

    void accept(Player player);
}
