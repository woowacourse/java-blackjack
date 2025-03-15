package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.player.Player;
import blackjack.util.GlobalValidator;

import java.util.List;

public final class PlayerBlackjackCardHand {
    
    private final BlackjackCardHand cardHand;
    private final Player player;
    
    public PlayerBlackjackCardHand(final BlackjackCardHand cardHand, final Player player) {
        GlobalValidator.validateNotNull(PlayerBlackjackCardHand.class, cardHand, player);
        this.cardHand = cardHand;
        this.player = player;
    }
    
    public static PlayerBlackjackCardHand createWithInitialCards(
            final Player player,
            final BlackjackCardHandInitializer initializer
    ) {
        GlobalValidator.validateNotNull(PlayerBlackjackCardHand.class, player, initializer);
        return new PlayerBlackjackCardHand(
                BlackjackCardHand.createWithInitialCards(initializer),
                player
        );
    }
    
    public List<Card> getInitialCards() {
        return cardHand.getCards().stream()
                .limit(2)
                .toList();
    }
    
    public String getPlayerName() {
        return player.getName();
    }
    
    public void addCard(final Card card) {
        cardHand.addCard(card);
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
    
    public boolean isAddedUpToMax() {
        return cardHand.isAddedUpToMax();
    }
}
