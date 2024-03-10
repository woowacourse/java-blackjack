package blackjack.domain.participant;

import blackjack.domain.Deck;

@FunctionalInterface
public interface PlayerTurn {
    void play(Player player, Deck deck);
}
