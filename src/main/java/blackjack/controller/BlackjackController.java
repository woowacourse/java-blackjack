package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.player.Players;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController implements Controller {
    
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
        final BlackjackGame game = BlackjackGame.from(players, bettingAmounts);
        outputView.outputInitialCards(game.getDealerHand(), game.getPlayerHands());
        blackjackService.addPlayerCards(
                game,
                outputView::outputAddingMessage,
                outputView::addTo21Warning,
                outputView::bustWarning,
                inputView::getAddingCardDecision,
                outputView::outputCardsAndSum
        );
        blackjackService.addDealerCards(game, outputView::outputDealerAddedCards);
        outputView.outputOpenCards(game.getDealerHand(), game.getPlayerHands());
        outputView.outputFinalProfit(game.getDealerHand(), game.getPlayerHands());
    }
}
