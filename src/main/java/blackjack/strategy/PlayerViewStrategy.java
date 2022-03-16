package blackjack.strategy;

import blackjack.domain.participant.Player;

@FunctionalInterface
public interface PlayerViewStrategy {

    void printOf(Player player);
}
