package blackjack.service;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;

import java.util.function.Consumer;
import java.util.function.Function;

public class BlackjackService {
    
    public void addPlayerCards(
            final BlackjackGame blackjackGame,
            final Consumer<String> playerTurnNotifier,
            final Runnable reached21Notifier,
            final Runnable bustNotifier,
            final Function<String, Boolean> addCardDecision,
            final Consumer<PlayerBettingBlackjackCardHand> playerHandNotifier
    ) {
        for (PlayerBettingBlackjackCardHand playerHand : blackjackGame.getPlayerHands()) {
            playerTurnNotifier.accept(playerHand.getPlayerName());
            if (playerHand.isAddedTo21()) {
                reached21Notifier.run();
                continue;
            }
            if (playerHand.isBust()) {
                bustNotifier.run();
                continue;
            }
            while (addCardDecision.apply(playerHand.getPlayerName())) {
                playerHand.addCard(blackjackGame.draw());
                playerHandNotifier.accept(playerHand);
            }
        }
    }
    
    public void addDealerCards(
            final BlackjackGame blackjackGame,
            final Consumer<Integer> dealerCardsAddedNotifier
    ) {
        final int addedSize = blackjackGame.startAddingAndGetAddedSize();
        dealerCardsAddedNotifier.accept(addedSize);
    }
}
