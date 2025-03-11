package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.player.Player;
import blackjack.util.GlobalValidator;

import java.util.List;

public final class PlayerBlackjackCardHand {
    
    private final BlackjackCardHand cardHand;
    private final Player player;
    
    public PlayerBlackjackCardHand(final Player player, final BlackjackCardHandInitializer initializer) {
        GlobalValidator.validateNotNull(this, player, initializer);
        this.cardHand = new BlackjackCardHand(initializer);
        this.player = player;
    }
    
    public List<Card> getInitialCards() {
        return List.of(cardHand.getCards().getFirst(), cardHand.getCards().get(1));
    }
    
    public void addCard(final Card card) {
        cardHand.addCard(card);
    }
    
    public String getPlayerName() {
        return player.getName();
    }
    
    public List<Card> getCards() {
        return cardHand.getCards();
    }
    
    public int getBlackjackSum() {
        return cardHand.getBlackjackSum();
    }
    
    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }
    
    public boolean isBust() {
        return cardHand.isBust();
    }
    
    public boolean isAddedTo21() {
        return getBlackjackSum() == 21;
    }
}
