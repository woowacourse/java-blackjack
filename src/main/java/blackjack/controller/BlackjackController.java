package blackjack.controller;

import blackjack.domain.BlackjackJudge;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController implements Controller {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    @Override
    public void run() {
        final BlackjackGame game = initializeGame();
        addCards(game);
        openCards(game);
        showFinalWinningStatus(game);
    }
    
    private BlackjackGame initializeGame() {
        BlackjackDeck deck = new BlackjackDeck();
        List<PlayerBlackjackCardHand> playerHands = createPlayerHands(deck);
        DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(deck);
        showInitialCards(dealerHand, playerHands);
        return new BlackjackGame(deck, dealerHand, playerHands);
    }
    
    private List<PlayerBlackjackCardHand> createPlayerHands(final BlackjackDeck deck) {
        final List<String> playerNames = inputView.getPlayerNames();
        final Players players = Players.from(playerNames);
        return players.toBlackjackCardHand(deck);
    }
    
    private void showInitialCards(
            final DealerBlackjackCardHand dealerHand,
            final List<PlayerBlackjackCardHand> playerHands
    ) {
        final List<String> playerNames = extractPlayerNames(playerHands);
        outputView.outputInitialCardOpeningMessage(playerNames);
        outputView.outputDealerCards(List.of(dealerHand.getInitialCard()));
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerHands) {
            outputView.outputPlayerCards(playerBlackjackCardHand);
        }
    }
    
    private List<String> extractPlayerNames(final List<PlayerBlackjackCardHand> playerHands) {
        return playerHands.stream()
                .map(PlayerBlackjackCardHand::getPlayerName)
                .toList();
    }
    
    private void addCards(final BlackjackGame game) {
        for (PlayerBlackjackCardHand playerHand : game.playerHands) {
            processPlayerAddCards(playerHand, game.deck);
        }
        processDealerAddCards(game.dealerHand, game.deck);
    }
    
    private void processPlayerAddCards(
            final PlayerBlackjackCardHand playerHand,
            final BlackjackDeck deck
    ) {
        outputView.outputAddingMessage(playerHand.getPlayerName());
        
        while (shouldAddCard(playerHand)) {
            playerHand.addCard(deck.draw());
            outputView.outputCardsAndSum(playerHand);
        }
    }
    
    private boolean shouldAddCard(final PlayerBlackjackCardHand playerHand) {
        if (playerHand.isAddedTo21()) {
            outputView.is21Warning();
            return false;
        }
        if (playerHand.isBust()) {
            outputView.bustWarning();
            return false;
        }
        return inputView.getAddingCardDecision(playerHand.getPlayerName());
    }
    
    private void processDealerAddCards(final DealerBlackjackCardHand dealerHand, final BlackjackDeck deck) {
        final int addedSize = dealerHand.startAddingAndGetAddedSize(deck);
        outputView.outputDealerAddedCards(addedSize);
    }
    
    private void openCards(final BlackjackGame game) {
        outputView.outputDealerCardsAndResult(game.dealerHand);
        for (PlayerBlackjackCardHand playerHand : game.playerHands) {
            outputView.outputPlayerCardsAndResult(playerHand);
        }
    }
    
    private void showFinalWinningStatus(final BlackjackGame game) {
        outputView.outputFinalWinOrLossMessage();
        final BlackjackJudge judge = new BlackjackJudge(game.dealerHand, game.playerHands);
        outputView.outputDealerFinalWinOrLoss(
                judge.getDealerWinningCount(),
                judge.getDealerDrawingCount(),
                judge.getDealerLosingCount()
        );
        for (PlayerBlackjackCardHand playerHand : game.playerHands) {
            outputView.outputPlayerFinalWinOrLoss(playerHand.getPlayerName(), judge.getWinningStatusOf(playerHand));
        }
    }
    
    private record BlackjackGame(
            BlackjackDeck deck,
            DealerBlackjackCardHand dealerHand,
            List<PlayerBlackjackCardHand> playerHands
    ) {
    }
}
