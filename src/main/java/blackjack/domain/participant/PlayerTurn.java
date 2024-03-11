package blackjack.domain.participant;

import blackjack.domain.card.Deck;

@FunctionalInterface
public interface PlayerTurn {
    void play(Player player, Deck deck);
}
