package blackjack.domain.player;

@FunctionalInterface
public interface AcceptStrategy {

    boolean accept(String name);
}
