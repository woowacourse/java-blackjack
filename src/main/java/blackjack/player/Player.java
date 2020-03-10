package blackjack.player;

import blackjack.player.card.CardBundle;

public abstract class Player {
    protected final CardBundle cardBundle;

    public Player(CardBundle cardBundle) {
        this.cardBundle = cardBundle;
    }
}
