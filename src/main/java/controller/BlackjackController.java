package controller;

import java.util.List;
import java.util.Map;
import model.betting.PlayersBetting;
import model.betting.BettingResult;
import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;
import model.winning.ParticipantWinningResult;
import model.participant.Players;
import model.turn.DealerTurn;
import model.turn.PlayerTurn;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Deck deck = Deck.of();
        List<String> playerNames = inputPlayersName();
        Players players = Players.from(playerNames);
        Dealer dealer = new Dealer();

        List<PlayerTurn> playerTurns = inputPlayersBetting(players);
        DealerTurn dealerTurn = new DealerTurn(dealer);

        PlayerTurnController playerTurnController = new PlayerTurnController(playerTurns);
        DealerTurnController dealerTurnController = new DealerTurnController(dealerTurn);

        playerTurnController.dealInitialCards(deck);
        dealerTurnController.dealInitialCards(deck);
        OutputView.printInitialDealResult(dealer, players);
        playerTurnController.betInsurance(dealer);
        playerTurnController.runParticipantsTurn(deck);
        dealerTurnController.runDealerTurn(deck);

        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        PlayersBetting playersBetting = PlayersBetting.from(playerTurns);
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
