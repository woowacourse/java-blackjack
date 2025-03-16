package controller;

import java.util.List;
import java.util.Map;
import model.card.Deck;
import model.betting.BettingResult;
import model.participant.Dealer;
import model.participant.Player;
import model.winning.ParticipantWinningResult;
import model.participant.Players;
import model.turn.DealerTurn;
import model.turn.PlayerTurn;
import model.turn.PlayerTurnManager;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> playerNames = inputPlayersName();
        Players players = Players.from(playerNames);
        Dealer dealer = new Dealer();
        Deck deck = Deck.of();
        List<PlayerTurn> startBetting = inputPlayersBetting(players);
        PlayerTurnManager playerTurnManager = new PlayerTurnManager(startBetting);
        DealerTurn dealerTurn = new DealerTurn(dealer);

        playerTurnManager.dealInitialCardsToAllPlayers(deck);
        dealerTurn.dealInitialCards(deck);
        OutputView.printInitialDealResult(dealer, players);
        playerTurnManager.betInsurance(dealer);
        playerTurnManager.runPlayerTurn(deck);
        dealerTurn.runDealerTurn(deck);

        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        BettingResult bettingResult = new BettingResult(playerTurnManager.getPlayersBet(), participantWinningResult);
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
