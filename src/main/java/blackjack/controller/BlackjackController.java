package blackjack.controller;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.domain.deck.CardDrawer;
import blackjack.domain.player.Players;
import blackjack.util.RetryHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public final class BlackjackController {
    
    private final InputView inputView;
    private final OutputView outputView;
    private final RetryHandler retryHandler;
    
    public BlackjackController(final InputView inputView, final OutputView outputView, final RetryHandler retryHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.retryHandler = retryHandler;
    }
    
    public void run() {
        final Players players = createPlayersUntilSuccess();
        final BlackjackDeck deck = new BlackjackDeck();
        final DealerBlackjackCardHand dealerHands = DealerBlackjackCardHand.createWithInitialCards(deck);
        final List<PlayerBettingBlackjackCardHand> playerHands = createPlayerHandsUntilSuccess(players, deck);
        outputView.outputInitialCards(dealerHands, playerHands);
        addPlayerCards(playerHands, deck);
        addDealerCards(dealerHands, deck);
        outputView.outputOpenCards(dealerHands, playerHands);
        outputView.outputFinalProfit(dealerHands, playerHands);
    }
    
    private Players createPlayersUntilSuccess() {
        return retryHandler.runWithRetry(() -> {
            final List<String> playerNames = inputView.getPlayerNames();
            return Players.from(playerNames);
        });
    }
    
    private List<PlayerBettingBlackjackCardHand> createPlayerHandsUntilSuccess(final Players players, final BlackjackDeck deck) {
        return retryHandler.runWithRetry(() -> {
            final List<Integer> bettingAmounts = inputView.getBettingAmounts(players.getPlayerNames());
            return players.toBlackjackBettingCardHand(deck, bettingAmounts);
        });
    }
    
    private void addPlayerCards(final List<PlayerBettingBlackjackCardHand> playerHands, final CardDrawer cardDrawer) {
        for (PlayerBettingBlackjackCardHand playerHand : playerHands) {
            outputView.outputAddingMessage(playerHand.getPlayerName());
            outputView.outputCardsAndSum(playerHand);
            startAddingPlayerCards(cardDrawer, playerHand);
        }
    }
    
    private void startAddingPlayerCards(final CardDrawer cardDrawer, final PlayerBettingBlackjackCardHand playerHand) {
        while (true) {
            if (playerHand.isAddedUpToMax()) {
                outputView.reachedMaxWarning();
                return;
            }
            if (playerHand.isBust()) {
                outputView.bustWarning();
                return;
            }
            if (!inputView.getAddingCardDecision(playerHand.getPlayerName())) {
                return;
            }
            playerHand.addCard(cardDrawer.draw());
            outputView.outputCardsAndSum(playerHand);
        }
    }
    
    private void addDealerCards(final DealerBlackjackCardHand dealerHand, final CardDrawer cardDrawer) {
        final int addedSize = dealerHand.startAddingAndGetAddedSize(cardDrawer);
        outputView.outputDealerAddedCards(addedSize);
    }
}
