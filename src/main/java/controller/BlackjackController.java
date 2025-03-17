package controller;

import java.util.List;
import java.util.Map;
import model.betting.PlayersBetting;
import model.card.Deck;
import model.betting.BettingResult;
import model.participant.Dealer;
import model.participant.Player;
import model.winning.ParticipantWinningResult;
import model.participant.Players;
import model.turn.DealerTurn;
import model.turn.PlayerTurn;
import model.turn.PlayersTurn;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> playerNames = inputPlayersName();
        Players players = Players.from(playerNames);
        Dealer dealer = new Dealer();
        List<PlayerTurn> startBetting = inputPlayersBetting(players);
        PlayersTurn playersTurn = new PlayersTurn(startBetting);
        DealerTurn dealerTurn = new DealerTurn(dealer);
        TurnController turnController = new TurnController(playersTurn, dealerTurn);

        turnController.dealInitialCards();
        OutputView.printInitialDealResult(dealer, players);
        turnController.betInsurance();
        turnController.runParticipantsTurn();

        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        PlayersBetting playersBetting = new PlayersBetting(playersTurn.getPlayersBet());
        BettingResult bettingResult = new BettingResult(playersBetting, participantWinningResult);
        Map<Player, Integer> finalProfitByPlayer = bettingResult.calculatePlayerBettingResult(players, dealer);
        int finalProfitByDealer = bettingResult.calculateDealerFinalResult(finalProfitByPlayer);

        OutputView.printFinalScore(dealer, players);
        printFinalGameResult(finalProfitByPlayer, finalProfitByDealer);
    }

    private List<String> inputPlayersName() {
        try {
            return InputView.readPlayerNames();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPlayersName();
        }
    }

    private List<PlayerTurn> inputPlayersBetting(Players players) {
        try {
            return InputView.startBettingTurn(players);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPlayersBetting(players);
        }
    }

    private void printFinalGameResult(Map<Player, Integer> playersProfit, int dealerProfit) {
        System.out.println();
        OutputView.printDealerFinalResult(dealerProfit);
        OutputView.printPlayerFinalResult(playersProfit);
    }
}
