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
        BlackjackGame blackjackGame = new BlackjackGame();

        blackjackGame.dealInitially(players, dealer);
        OutputView.printInitialDealResult(dealer, players);

        blackjackGame.runPlayerTurn(players);
        blackjackGame.runDealerTurn(dealer);
        OutputView.printFinalScore(dealer, players);

        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        bettingResult.calculatePlayerBettingResult(players, participantWinningResult);
        printFinalGameResult(bettingResult);
    }

    private void printFinalGameResult(BettingResult bettingResult) {
        OutputView.printDealerFinalResult(bettingResult);
        OutputView.printPlayerFinalResult(bettingResult);
    }
}
