package blackjack.controller;

import blackjack.domain.BettingBoard;
import blackjack.domain.WinningStatus;
import blackjack.domain.money.BlackjackBettingMoney;
import blackjack.domain.money.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.HashMap;
import java.util.List;

import blackjack.domain.BlackjackJudge;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;

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
        final BettingBoard bettingBoard = new BettingBoard();
        List<String> playerNames = inputView.getPlayerNames();
        final Players players = Players.createPlayers(playerNames);
        processBetting(playerNames, players, bettingBoard);
        
        final List<PlayerBlackjackCardHand> playerBlackjackCardHands = createPlayerBlackjackCardHands(players, deck);
        final DealerBlackjackCardHand dealerBlackjackCardHand = new DealerBlackjackCardHand(deck);
        
        processBlackjackGame(dealerBlackjackCardHand, playerBlackjackCardHands, deck);
        processResults(dealerBlackjackCardHand, playerBlackjackCardHands, bettingBoard);
    }
    
    private void processBetting(List<String> playerNames, Players players, BettingBoard bettingBoard) {
        Map<String, BlackjackBettingMoney> bettings = new HashMap<>();
        for (String playerName : playerNames) {
            bettings.put(playerName, new BlackjackBettingMoney(inputView.getBettingMoney(playerName)));
        }
        players.bet(bettings, bettingBoard);
    }
    
    private List<PlayerBlackjackCardHand> createPlayerBlackjackCardHands(Players players, BlackjackDeck deck) {
        return players.drawCardsAndGetCardHands(deck);
    }
    
    private void processBlackjackGame(DealerBlackjackCardHand dealerBlackjackCardHand, List<PlayerBlackjackCardHand> playerBlackjackCardHands, BlackjackDeck deck) {
        processInitialCardOpening(dealerBlackjackCardHand, playerBlackjackCardHands);
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerBlackjackCardHands) {
            processPlayerAddingCards(playerBlackjackCardHand, deck);
        }
        processDealerAddingCards(dealerBlackjackCardHand, deck);
        processCardOpening(dealerBlackjackCardHand, playerBlackjackCardHands);
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
    
    private void processPlayerAddingCards(final PlayerBlackjackCardHand playerBlackjackCardHand,
                                           final BlackjackDeck deck) {
        outputView.outputAddingMessage(playerBlackjackCardHand.getPlayerName());
        boolean addingCardDecision;
        do {
            if (playerBlackjackCardHand.isFinished()) {
                outputView.outputCardAddingLimitMessage();
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
    
    private void processResults(
            final DealerBlackjackCardHand dealerBlackjackCardHand,
            final List<PlayerBlackjackCardHand> playerBlackjackCardHands,
            final BettingBoard bettingBoard
    ) {
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerBlackjackCardHand, playerBlackjackCardHands);

        Map<Player, WinningStatus> winningStatusOfAllPlayers = blackjackJudge.getWinningStatusOfAllPlayers();
        int dealerProfit = bettingBoard.getDealerProfit(winningStatusOfAllPlayers);
        Map<Player, Money> playersProfit = new HashMap<>();
        for (PlayerBlackjackCardHand playerBlackjackCardHand : playerBlackjackCardHands) {
            Player player = playerBlackjackCardHand.getPlayer();
            playersProfit.put(player, bettingBoard.getProfit(player, blackjackJudge.getWinningStatusOf(playerBlackjackCardHand)));
        }
        
        outputView.outputTotalProfit(dealerProfit, playersProfit);
    }
}
