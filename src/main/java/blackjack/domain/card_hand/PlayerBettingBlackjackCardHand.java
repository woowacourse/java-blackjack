package blackjack.domain.card_hand;

import blackjack.domain.WinningStatus;
import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.player.Player;
import blackjack.util.GlobalValidator;

import java.util.List;

public final class PlayerBettingBlackjackCardHand {
    
    private static final int MIN_BETTING_AMOUNT = 1000;
    private static final int MAX_BETTING_AMOUNT = 100000;
    private static final int BETTING_UNIT = 1000;
    private static final double BLACK_JACK_PROFIT_RATIO = 1.5;
    
    private final PlayerBlackjackCardHand hand;
    private final int bettingAmount;
    
    private PlayerBettingBlackjackCardHand(final PlayerBlackjackCardHand hand, final int bettingAmount) {
        GlobalValidator.validateNotNull(PlayerBettingBlackjackCardHand.class, hand, bettingAmount);
        validateBettingAmount(bettingAmount);
        validateBettingUnit(bettingAmount);
        this.hand = hand;
        this.bettingAmount = bettingAmount;
    }
    
    private void validateBettingAmount(final int bettingAmount) {
        if (bettingAmount < MIN_BETTING_AMOUNT || bettingAmount > MAX_BETTING_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 %d원 이상, %d원 이하여야 합니다.".formatted(MIN_BETTING_AMOUNT, MAX_BETTING_AMOUNT));
        }
    }
    
    private void validateBettingUnit(final int bettingAmount) {
        if (bettingAmount % BETTING_UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 %d원 단위여야 합니다.".formatted(BETTING_UNIT));
        }
    }
    
    public static PlayerBettingBlackjackCardHand createWithInitialCards(
            final Player player,
            final int bettingAmount,
            final BlackjackCardHandInitializer initializer
    ) {
        GlobalValidator.validateNotNull(PlayerBettingBlackjackCardHand.class, player, initializer);
        return new PlayerBettingBlackjackCardHand(PlayerBlackjackCardHand.createWithInitialCards(player, initializer), bettingAmount);
    }
    
    public double calculateIncome(final DealerBlackjackCardHand dealerHand) {
        final WinningStatus winningStatus = WinningStatus.determineWinningStatus(hand, dealerHand);
        if (isBlackjackWinning(winningStatus)) {
            return bettingAmount * BLACK_JACK_PROFIT_RATIO;
        }
        return switch (winningStatus) {
            case 플레이어_승리 -> bettingAmount;
            case 무승부 -> 0;
            case 플레이어_패배 -> -bettingAmount;
        };
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
