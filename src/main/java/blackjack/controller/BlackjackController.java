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
        BlackjackDeck deck = new BlackjackDeck();
        List<PlayerBlackjackCardHand> playerHands = createPlayerHands(deck);
        DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(deck);
        outputView.outputInitialCards(dealerHand, playerHands);
        addCards(dealerHand, playerHands, deck);
        outputView.outputOpenCards(dealerHand, playerHands);
        outputView.outputFinalWinOrLoss(new BlackjackJudge(dealerHand, playerHands), playerHands);
    }
    
    private List<PlayerBlackjackCardHand> createPlayerHands(final BlackjackDeck deck) {
        final List<String> playerNames = inputView.getPlayerNames();
        final Players players = Players.from(playerNames);
        return players.toBlackjackCardHand(deck);
    }
    
    private void addCards(
            final DealerBlackjackCardHand dealerHand,
            final List<PlayerBlackjackCardHand> playerHands,
            final BlackjackDeck deck
    ) {
        for (PlayerBlackjackCardHand playerHand : playerHands) {
            processPlayerAddCards(playerHand, deck);
        }
        processDealerAddCards(dealerHand, deck);
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
            outputView.addTo21Warning();
            return false;
        }
        if (playerHand.isBust()) {
            outputView.bustWarning();
            return false;
        }
        return inputView.getAddingCardDecision(playerHand.getPlayerName());
    }
    
    private void processDealerAddCards(
            final DealerBlackjackCardHand dealerHand,
            final BlackjackDeck deck
    ) {
        final int addedSize = dealerHand.startAddingAndGetAddedSize(deck);
        outputView.outputDealerAddedCards(addedSize);
    }
}
