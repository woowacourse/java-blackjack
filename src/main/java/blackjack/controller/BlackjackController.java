package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackjackJudge;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.domain.Player;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController implements Controller {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    @Override
    public void run() {
        final BlackjackDeck deck = new BlackjackDeck();
        final List<PlayerBlackjackCardHand> playerBlackjackCardHands = createPlayerCardHands(deck);
        final DealerBlackjackCardHand dealerBlackjackCardHand = new DealerBlackjackCardHand(deck);
        processInitialCardOpening(dealerBlackjackCardHand, playerBlackjackCardHands);
        
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerBlackjackCardHands) {
            processPlayerAddingCards(playerBlackjackCardHand, deck);
        }
        processDealerAddingCards(dealerBlackjackCardHand, deck);
        
        processCardOpening(dealerBlackjackCardHand, playerBlackjackCardHands);
        processFinalWinOrLoss(dealerBlackjackCardHand, playerBlackjackCardHands);
    }
    
    private List<PlayerBlackjackCardHand> createPlayerCardHands(final BlackjackDeck deck) {
        final List<String> playerNames = inputView.getPlayerNames();
        final List<Player> players = Player.createPlayers(playerNames);
        return players.stream()
                .map(player -> new PlayerBlackjackCardHand(player, deck))
                .toList();
    }
    
    private void processInitialCardOpening(final DealerBlackjackCardHand dealerBlackjackCardHand, final List<PlayerBlackjackCardHand> playerBlackjackCardHands) {
        final List<String> playerNames = playerBlackjackCardHands.stream()
                .map(PlayerBlackjackCardHand::getPlayerName)
                .toList();
        outputView.outputInitialCardOpeningMessage(playerNames);
        outputView.outputDealerCards(dealerBlackjackCardHand.getInitialCards());
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerBlackjackCardHands) {
            outputView.outputPlayerCards(playerBlackjackCardHand.getPlayerName(), playerBlackjackCardHand.getInitialCards());
        }
    }
    
    private void processPlayerAddingCards(final PlayerBlackjackCardHand playerBlackjackCardHand, final BlackjackDeck deck) {
        outputView.outputAddingMessage(playerBlackjackCardHand.getPlayerName());
        boolean addingCardDecision;
        do {
            if (playerBlackjackCardHand.isAddedTo21()) {
                outputView.is21Warning();
                break;
            }
            if (playerBlackjackCardHand.isBust()) {
                outputView.bustWarning();
                break;
            }
            addingCardDecision = inputView.getAddingCardDecision(playerBlackjackCardHand.getPlayerName());
            if (addingCardDecision) {
                playerBlackjackCardHand.addCard(deck.draw());
                outputView.outputCardsAndSum(playerBlackjackCardHand.getCards(), playerBlackjackCardHand.getBlackjackSum());
            }
        } while (addingCardDecision);
    }
    
    private void processDealerAddingCards(final DealerBlackjackCardHand dealerBlackjackCardHand, final BlackjackDeck deck) {
        final int beforeCount = dealerBlackjackCardHand.getCards().size();
        dealerBlackjackCardHand.startAdding(deck);
        final int afterCount = dealerBlackjackCardHand.getCards().size();
        outputView.outputDealerAddedCards(afterCount - beforeCount);
    }
    
    private void processCardOpening(final DealerBlackjackCardHand dealerBlackjackCardHand, final List<PlayerBlackjackCardHand> playerBlackjackCardHands) {
        outputView.outputDealerCardsAndResult(dealerBlackjackCardHand.getCards(), dealerBlackjackCardHand.getBlackjackSum());
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerBlackjackCardHands) {
            outputView.outputPlayerCardsAndResult(playerBlackjackCardHand.getPlayerName(), playerBlackjackCardHand.getCards(), playerBlackjackCardHand.getBlackjackSum());
        }
    }
    
    private void processFinalWinOrLoss(final DealerBlackjackCardHand dealerBlackjackCardHand, final List<PlayerBlackjackCardHand> playerBlackjackCardHands) {
        outputView.outputFinalWinOrLossMessage();
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerBlackjackCardHand, playerBlackjackCardHands);
        outputView.outputDealerFinalWinOrLoss(
                blackjackJudge.getDealerWinningCount(),
                blackjackJudge.getDealerDrawingCount(),
                blackjackJudge.getDealerLosingCount()
        );
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerBlackjackCardHands) {
            outputView.outputPlayerFinalWinOrLoss(playerBlackjackCardHand.getPlayerName(), blackjackJudge.getWinningStatusOf(playerBlackjackCardHand));
        }
    }
}
