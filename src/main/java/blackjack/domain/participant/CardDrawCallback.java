package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public interface CardDrawCallback {

    boolean isContinuable(Player player);
    void onUpdate(Player player);

    default void drawCard(Player player, Deck deck) {
        while (isContinuable(player) && !player.isBurst()) {
            player.drawCard(deck);
            this.onUpdate(player);
        }
    }
}
