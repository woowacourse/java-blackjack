package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.player.Player;
import blackjack.util.GlobalValidator;

import java.util.List;

public final class PlayerBlackjackCardHand extends AbstractBlackjackCardHand {
    
    private final Player player;
    
    public PlayerBlackjackCardHand(final BlackjackCardHandInitializer initializer, final Player player) {
        super(initializer);
        GlobalValidator.validateNotNull(player);
        this.player = player;
    }
    
    @Override
    public List<Card> getInitialCards() {
        return getCards().stream()
                .limit(2)
                .toList();
    }
    
    @Override
    public void addCard(final Card card) {
        super.addCard(card);
    }
    
    public String getPlayerName() {
        return player.getName();
    }
}
