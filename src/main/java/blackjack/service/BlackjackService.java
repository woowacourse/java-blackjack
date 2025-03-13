package blackjack.service;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;
import blackjack.domain.deck.CardDrawer;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackjackService {
    
    public void addPlayerCards(
            final List<PlayerBettingBlackjackCardHand> playerHands,
            final CardDrawer cardDrawer,
            final Consumer<String> playerTurnNotifier,
            final Runnable reachedMaxNotifier,
            final Runnable bustNotifier,
            final Function<String, Boolean> addCardDecision,
            final Consumer<PlayerBettingBlackjackCardHand> playerHandNotifier
    ) {
        for (PlayerBettingBlackjackCardHand playerHand : playerHands) {
            playerTurnNotifier.accept(playerHand.getPlayerName());
            playerHandNotifier.accept(playerHand);
            startAddingPlayerCards(cardDrawer, reachedMaxNotifier, bustNotifier, addCardDecision, playerHandNotifier, playerHand);
        }
    }
    
    private void startAddingPlayerCards(
            final CardDrawer cardDrawer,
            final Runnable reachedMaxNotifier,
            final Runnable bustNotifier,
            final Function<String, Boolean> addCardDecision,
            final Consumer<PlayerBettingBlackjackCardHand> playerHandNotifier,
            final PlayerBettingBlackjackCardHand playerHand
    ) {
        while(true) {
            if (playerHand.isAddedUpToMax()) {
                reachedMaxNotifier.run();
                break;
            }
            if (playerHand.isBust()) {
                bustNotifier.run();
                break;
            }
            if (addCardDecision.apply(playerHand.getPlayerName())) {
                playerHand.addCard(cardDrawer.draw());
                playerHandNotifier.accept(playerHand);
            }
        }
    }
    
    public void addDealerCards(
            final DealerBlackjackCardHand dealerHand,
            final CardDrawer cardDrawer,
            final Consumer<Integer> dealerCardsAddedNotifier
    ) {
        final int addedSize = dealerHand.startAddingAndGetAddedSize(cardDrawer);
        dealerCardsAddedNotifier.accept(addedSize);
    }
}
