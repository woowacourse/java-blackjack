package blackjack.model;

import blackjack.model.participant.Player;

@FunctionalInterface
public interface PlayerHandVisualizer {

    void accept(Player player);
}
