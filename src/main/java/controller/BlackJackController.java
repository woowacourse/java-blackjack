package controller;

import domain.BettingAmount;
import domain.BettingManager;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import service.BlackJackGame;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public BlackJackController() {
    }

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(inputView.requestPlayerName());
        BettingManager bettingManager = blackJackGame.createBettingManager(
                requestBettingAmounts(blackJackGame.getPlayerNamesToString()));
        printInitialDistribution(blackJackGame);

        progress(blackJackGame);
        end(blackJackGame, bettingManager);
    }

    private List<BettingAmount> requestBettingAmounts(List<String> playerNames) {
        List<BettingAmount> bettingAmounts = new ArrayList<>();
        for (String name : playerNames) {
            bettingAmounts.add(BettingAmount.fromPlayer(inputView.askPlayerBettingAmount(name)));
        }
        return bettingAmounts;
    }

    private void printInitialDistribution(BlackJackGame blackJackGame) {
        outputView.printFirstCardDistribution(blackJackGame.getDealerNameToString(),
                blackJackGame.getPlayerNamesToString());
        outputView.printAllParticipantsInitialCard(blackJackGame.getAllParticipantsNameToString(),
                blackJackGame.getParticipantsInitialCards());
    }

    private void progress(BlackJackGame blackJackGame) {
        progressPlayers(blackJackGame);
        progressDealer(blackJackGame);
    }

    private void progressPlayers(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            requestPlayerMoreCard(blackJackGame, player);
        }
    }

    private void requestPlayerMoreCard(BlackJackGame blackJackGame, Player player) {
        boolean isCardRequested = true;

        while (player.isMoreCardAble() && isCardRequested) {
            isCardRequested = inputView.askMoreCard(player.getNameToValue());
            blackJackGame.progressPlayer(player, isCardRequested);
            outputView.printCardStatus(player.getNameToValue(),
                    blackJackGame.getStatusFromCards(player.getCardList()));
        }
    }

    private void progressDealer(BlackJackGame blackJackGame) {
        int dealerMoreCardCount = blackJackGame.progressDealer();
        outputView.printDealerMoreCard(blackJackGame.getDealerNameToString(), dealerMoreCardCount);
    }

    private void end(BlackJackGame blackJackGame, BettingManager bettingManager) {
        outputView.printAllParticipantsCardAndScore(blackJackGame.getAllParticipantsNameToString(),
                blackJackGame.getAllParticipantsCards(), blackJackGame.getAllParticipantsTotalScore());
        outputView.printFinalResult(blackJackGame.calculateTotalRevenue(bettingManager));
    }

}
