package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackjackJudge;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.card_hand.DealerCardHand;
import blackjack.domain.card_hand.PlayerCardHand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void run() {
        final Deck deck = new Deck();
        final List<PlayerCardHand> playerCardHands = createPlayerCardHands(deck);
        final DealerCardHand dealerCardHand = new DealerCardHand(deck);
        processInitialCardOpening(dealerCardHand, playerCardHands);
        
        for (PlayerCardHand playerCardHand : playerCardHands) {
            processPlayerAddingCards(playerCardHand, deck);
        }
        processDealerAddingCards(dealerCardHand, deck);
        
        processCardOpening(dealerCardHand, playerCardHands);
        processFinalWinOrLoss(dealerCardHand, playerCardHands);
    }
    
    private List<PlayerCardHand> createPlayerCardHands(final Deck deck) {
        final List<String> playerNames = inputView.getPlayerNames();
        final List<Player> players = Player.createPlayers(playerNames);
        return players.stream()
                .map(player -> new PlayerCardHand(player, deck))
                .toList();
    }
    
    private void processInitialCardOpening(final DealerCardHand dealerCardHand, final List<PlayerCardHand> playerCardHands) {
        final List<String> playerNames = playerCardHands.stream()
                .map(PlayerCardHand::getPlayerName)
                .toList();
        outputView.outputInitialCardOpeningMessage(playerNames);
        outputView.outputDealerCards(dealerCardHand.getInitialCards());
        for (PlayerCardHand playerCardHand : playerCardHands) {
            outputView.outputPlayerCards(playerCardHand.getPlayerName(), playerCardHand.getInitialCards());
        }
    }
    
    private void processPlayerAddingCards(final PlayerCardHand playerCardHand, final Deck deck) {
        outputView.outputAddingMessage(playerCardHand.getPlayerName());
        boolean addingCardDecision;
        do {
            if (playerCardHand.is21()) {
                outputView.is21Warning();
                break;
            }
            if (playerCardHand.isBurst()) {
                outputView.burstWarning();
                break;
            }
            addingCardDecision = inputView.getAddingCardDecision(playerCardHand.getPlayerName());
            if (addingCardDecision) {
                playerCardHand.addCard(deck.draw());
                outputView.outputCardsAndSum(playerCardHand.getCards(), playerCardHand.getSum());
            }
        } while (addingCardDecision);
    }
    
    private void processDealerAddingCards(final DealerCardHand dealerCardHand, final Deck deck) {
        final int beforeCount = dealerCardHand.getCards().size();
        dealerCardHand.startAdding(deck);
        final int afterCount = dealerCardHand.getCards().size();
        outputView.outputDealerAddedCards(afterCount - beforeCount);
    }
    
    private void processCardOpening(final DealerCardHand dealerCardHand, final List<PlayerCardHand> playerCardHands) {
        outputView.outputDealerCardsAndResult(dealerCardHand.getCards(), dealerCardHand.getSum());
        for (PlayerCardHand playerCardHand : playerCardHands) {
            outputView.outputPlayerCardsAndResult(playerCardHand.getPlayerName(), playerCardHand.getCards(), playerCardHand.getSum());
        }
    }
    
    private void processFinalWinOrLoss(final DealerCardHand dealerCardHand, final List<PlayerCardHand> playerCardHands) {
        outputView.outputFinalWinOrLossMessage();
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerCardHand, playerCardHands);
        outputView.outputDealerFinalWinOrLoss(
                blackjackJudge.getDealerWinningCount(),
                blackjackJudge.getDealerDrawingCount(),
                blackjackJudge.getDealerLosingCount()
        );
        for (PlayerCardHand playerCardHand : playerCardHands) {
            outputView.outputPlayerFinalWinOrLoss(playerCardHand.getPlayerName(), blackjackJudge.getWinningStatusOf(playerCardHand));
        }
    }
}
