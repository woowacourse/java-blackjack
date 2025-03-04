package blackjack.domain.card_hand;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;
import blackjack.domain.Player;

public class PlayerCardHand extends CardHand {
    
    private final Player player;
    
    public PlayerCardHand(final Player player, final CardHandInitializer initializer) {
        super(initializer);
        this.player = player;
    }
    
    public void addCard(final Card card) {
        cards.add(card);
    }
    
    public String getPlayerName() {
        return player.getName();
    }
}
