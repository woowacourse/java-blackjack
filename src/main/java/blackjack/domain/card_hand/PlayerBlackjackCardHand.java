package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.player.Player;

import java.util.List;

public final class PlayerBlackjackCardHand {
    
    private final BlackjackCardHand cardHand;
    private final Player player;
    
    public PlayerBlackjackCardHand(final Player player, final BlackjackCardHandInitializer initializer) {
        validateNotNull(player, initializer);
        this.cardHand = new BlackjackCardHand(initializer);
        this.player = player;
    }
    
    private void validateNotNull(final Player player, final BlackjackCardHandInitializer initializer) {
        if (player == null) {
            throw new IllegalArgumentException("플레이어는 null이 될 수 없습니다.");
        }
        if (initializer == null) {
            throw new IllegalArgumentException("초기 카드 지급 방식은 null이 될 수 없습니다.");
        }
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
