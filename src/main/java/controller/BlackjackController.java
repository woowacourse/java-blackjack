package controller;

import java.util.List;
import java.util.Map;
import model.result.BettingResult;
import model.BlackjackGame;
import model.participant.Dealer;
import model.participant.Player;
import model.result.ParticipantWinningResult;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> playerNames = InputView.readPlayerNames();
        Players players = Players.from(playerNames);
        Map<Player, Integer> startBetting = InputView.inputBettingPrice(players);
        BettingResult bettingResult = new BettingResult(startBetting);
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame(players, dealer);

        blackjackGame.dealInitially();
        blackjackGame.runGameTurn();
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        bettingResult.calculatePlayerBettingResult(players, participantWinningResult);

        OutputView.printFinalScore(dealer, players);
        printFinalGameResult(bettingResult);
    }

    private void printFinalGameResult(BettingResult bettingResult) {
        System.out.println();
        OutputView.printDealerFinalResult(bettingResult);
        OutputView.printPlayerFinalResult(bettingResult);
    }
}
