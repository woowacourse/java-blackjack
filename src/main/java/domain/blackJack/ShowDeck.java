package domain.blackJack;

import domain.participant.Player;

@FunctionalInterface
public interface ShowDeck {
    void show(Player player);
}
