package blackjack.domain.participant;

@FunctionalInterface
public interface WagerReader {

    int wagerOf(Player player);
}
