package blackjack.domain.card_hand;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.player.Player;

public final class PlayerBlackjackCardHand implements BlackjackWinDeterminable {
    
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACKJACK_SUM = 21;
    
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
        if (getBlackjackSum() >= BLACKJACK_SUM) {
            throw new IllegalStateException("카드를 더 받을 수 없습니다.");
        }
        cardHand.addCard(card);
    }
    
    public String getPlayerName() {
        return player.getName();
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public List<Card> getCards() {
        return cardHand.getCards();
    }
    
    @Override
    public int getBlackjackSum() {
        return cardHand.getBlackjackSum();
    }
    
    @Override
    public int getSize() {
        return cardHand.getCards().size();
    }
    
    public boolean isAddedTo21() {
        return getBlackjackSum() == BLACKJACK_SUM;
    }
    
    public boolean isBust() {
        return getBlackjackSum() > BUST_THRESHOLD;
    }
}
