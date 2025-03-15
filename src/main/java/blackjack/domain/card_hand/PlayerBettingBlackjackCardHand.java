package blackjack.domain.card_hand;

import blackjack.domain.BlackjackBetting;
import blackjack.domain.WinningStatus;
import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.player.Player;
import blackjack.util.GlobalValidator;

import java.util.List;

public final class PlayerBettingBlackjackCardHand {
    
    private final PlayerBlackjackCardHand hand;
    private final BlackjackBetting bettingAmount;
    
    private PlayerBettingBlackjackCardHand(final PlayerBlackjackCardHand hand, final BlackjackBetting bettingAmount) {
        GlobalValidator.validateNotNull(hand, bettingAmount);
        this.hand = hand;
        this.bettingAmount = bettingAmount;
    }
    
    public static PlayerBettingBlackjackCardHand createWithInitialCards(
            final Player player,
            final int bettingAmount,
            final BlackjackCardHandInitializer initializer
    ) {
        GlobalValidator.validateNotNull(player, initializer);
        return new PlayerBettingBlackjackCardHand(
                PlayerBlackjackCardHand.createWithInitialCards(player, initializer),
                new BlackjackBetting(bettingAmount)
        );
    }
    
    public double calculateIncome(final DealerBlackjackCardHand dealerHand) {
        final WinningStatus winningStatus = WinningStatus.determineWinningStatus(hand, dealerHand);
        return bettingAmount.calculateIncome(winningStatus, isBlackjackWinning(winningStatus));
    }
    
    private boolean isBlackjackWinning(final WinningStatus winningStatus) {
        return winningStatus == WinningStatus.플레이어_승리 && hand.isBlackjack();
    }
    
    public String getPlayerName() {
        return hand.getPlayerName();
    }
    
    public List<Card> getInitialCards() {
        return hand.getInitialCards();
    }
    
    public List<Card> getCards() {
        return hand.getCards();
    }
    
    public int getBlackjackSum() {
        return hand.getBlackjackSum();
    }
    
    public boolean isAddedUpToMax() {
        return hand.isAddedUpToMax();
    }
    
    public boolean isBust() {
        return hand.isBust();
    }
    
    public void addCard(final Card card) {
        hand.addCard(card);
    }
}
