package blackjack.controller;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.domain.player.Players;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public final class BlackjackController implements Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(final InputView inputView, final OutputView outputView, final BlackjackService blackjackService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackService = blackjackService;
    }

    @Override
    public void run() {
        final List<String> playerNames = inputView.getPlayerNames();
        final Players players = Players.from(playerNames);
        final List<Integer> bettingAmounts = inputView.getBettingAmounts(playerNames);

        final BlackjackDeck deck = new BlackjackDeck();
        final List<PlayerBettingBlackjackCardHand> playerHands = players.toBlackjackBettingCardHand(deck, bettingAmounts);
        final DealerBlackjackCardHand dealerHands = DealerBlackjackCardHand.createWithInitialCards(deck);

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
}
