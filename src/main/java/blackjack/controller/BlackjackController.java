package blackjack.controller;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.domain.player.Players;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public final class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(final InputView inputView, final OutputView outputView, final BlackjackService blackjackService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackService = blackjackService;
    }

    public void run() {
        final Players players = createPlayersUntilSuccess();
        final BlackjackDeck deck = new BlackjackDeck();
        final DealerBlackjackCardHand dealerHands = DealerBlackjackCardHand.createWithInitialCards(deck);
        final List<PlayerBettingBlackjackCardHand> playerHands = createPlayerHandsUntilSuccess(players, deck);
        outputView.outputInitialCards(dealerHands, playerHands);
        blackjackService.addPlayerCards(
                playerHands,
                deck,
                outputView::outputAddingMessage,
                outputView::reachedMaxWarning,
                outputView::bustWarning,
                inputView::getAddingCardDecision,
                outputView::outputCardsAndSum
        );
        blackjackService.addDealerCards(dealerHands, deck, outputView::outputDealerAddedCards);
        outputView.outputOpenCards(dealerHands, playerHands);
        outputView.outputFinalProfit(dealerHands, playerHands);
    }
    
    private Players createPlayersUntilSuccess() {
        try {
            final List<String> playerNames = inputView.getPlayerNames();
            return Players.from(playerNames);
        } catch (Exception e) {
            outputView.outputExceptionMessage(e);
            return createPlayersUntilSuccess();
        }
    }
    
    private List<PlayerBettingBlackjackCardHand> createPlayerHandsUntilSuccess(final Players players, final BlackjackDeck deck) {
        try {
            final List<Integer> bettingAmounts = inputView.getBettingAmounts(players.getPlayerNames());
            return players.toBlackjackBettingCardHand(deck, bettingAmounts);
        } catch (Exception e) {
            outputView.outputExceptionMessage(e);
            return createPlayerHandsUntilSuccess(players, deck);
        }
    }
}
