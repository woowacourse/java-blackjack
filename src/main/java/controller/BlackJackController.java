package controller;

import domain.AnswerCommand;
import domain.Betting;
import domain.GameManager;
import domain.card.CardGroup;
import domain.card.Deck;
import domain.card.RandomCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.PlayerGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.LoopTemplate;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void gameStart() {
        final List<String> playerNames = LoopTemplate.tryCatchLoop(inputView::readPlayers);
        final PlayerGroup playerGroup = LoopTemplate.tryCatchLoop(() -> PlayerGroup.of(playerNames));
        collectPlayerBets(playerNames, playerGroup);
        final Dealer dealer = new Dealer(new CardGroup(new ArrayList<>()));
        final Deck deck = Deck.of(new RandomCardGenerator());
        final GameManager gameManager = new GameManager(dealer, playerGroup, deck);
        gameManager.initializeGame(playerNames);
        outputView.printDealerAndPlayersCards(dealer, playerGroup.getPlayers());
        requestHit(playerNames, gameManager);
        printDealerReceiveCardCount(gameManager);
        outputView.printGamerCardsAndScore(dealer, playerGroup.getPlayers());
        responseBettingOfReturn(gameManager);
    }

    private void collectPlayerBets (final List<String> playerNames, final PlayerGroup playerGroup) {
        for (String playerName : playerNames) {
            final Betting betting = LoopTemplate.tryCatchLoop(
                    () -> new Betting(inputView.readBettingAmount(playerName)));
            playerGroup.betAmountByPlayerName(playerName, betting);
        }
    }

    private void responseBettingOfReturn(final GameManager gameManager) {
        final double dealerBettingAmountOfReturn = gameManager.calculateDealerBettingAmountOfReturn();
        final Map<String, Double> playerBettingAmountOfReturn = gameManager.calculatePlayerBettingAmountOfReturn();
        outputView.printBettingAmountOfReturn(dealerBettingAmountOfReturn, playerBettingAmountOfReturn);
    }

    private void requestHit(final List<String> playerNames, final GameManager gameManager) {
        for (String playerName : playerNames) {
            processPlayerHit(playerName, gameManager);
        }
    }

    private void processPlayerHit(final String playerName, final GameManager gameManager) {
        if (!gameManager.canPlayerReceiveCard(playerName)) {
            return;
        }
        final boolean shouldContinue = gameManager.shouldContinuePlayerHit(playerName,
                requestAnswerCommand(playerName));
        outputView.printPlayerCards(playerName, gameManager.getPlayerCardsByName(playerName));
        if (shouldContinue) {
            processPlayerHit(playerName, gameManager);
        }
    }

    private AnswerCommand requestAnswerCommand(final String playerName) {
        return LoopTemplate.tryCatchLoop(() -> inputView.readAnswer(playerName));
    }

    private void printDealerReceiveCardCount(final GameManager gameManager) {
        final int count = gameManager.giveCardsToDealer();
        if (count > 0) {
            outputView.printDealerReceivedCardCount(count);
        }
    }
}
