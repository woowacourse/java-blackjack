package blackjack.domain.player;

import blackjack.domain.card.CardBundle;

public class Gambler extends Player {
    public Gambler(CardBundle cardBundle, PlayerInfo playerInfo) {
        super(cardBundle, playerInfo);
    }

    @Override
    public boolean isDrawable() {
        return isNotBurst() && isNotBlackjack();
    }
}
