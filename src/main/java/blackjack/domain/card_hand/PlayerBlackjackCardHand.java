package blackjack.domain.card_hand;

import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;
import blackjack.domain.Player;

public class PlayerBlackjackCardHand implements BlackjackCardHand {
    
    private final CardHand cardHand;
    private final Player player;
    
    public PlayerBlackjackCardHand(final Player player, final CardHandInitializer initializer) {
        this.cardHand = new CardHand(initializer);
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
    
    @Override
    public int getSum() {
        return cardHand.getSum();
    }
    
    @Override
    public int getSize() {
        return cardHand.getCards().size();
    }
    
    public boolean isAddedTo21() {
        return getSum() == 21;
    }
    
    public boolean isBurst() {
        return getSum() > 21;
    }
}
