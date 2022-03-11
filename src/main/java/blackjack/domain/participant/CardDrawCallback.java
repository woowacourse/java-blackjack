package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public interface CardDrawCallback {

    boolean isContinuable(final String playerName);

    void onUpdate(Player player);

    default void drawCard(Player player, Deck deck) {
        while (isContinuable(player.getName()) && !player.isBurst()) {
            player.drawCard(deck);
            this.onUpdate(player);
        }
    }
}
