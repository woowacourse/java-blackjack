package controller;

import java.util.List;
import java.util.Map;
import model.card.Deck;
import model.result.BettingResult;
import model.participant.Dealer;
import model.participant.Player;
import model.result.ParticipantWinningResult;
import model.participant.Players;
import model.turn.DealerTurn;
import model.turn.PlayerTurn;
import model.turn.PlayerTurnManager;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> playerNames = InputView.readPlayerNames();
        Players players = Players.from(playerNames);
        Dealer dealer = new Dealer();
        Deck deck = Deck.of();
        List<PlayerTurn> startBetting = InputView.startBettingTurn(players);
        PlayerTurnManager playerTurnManager = new PlayerTurnManager(startBetting);
        DealerTurn dealerTurn = new DealerTurn(dealer);

        playerTurnManager.dealInitialCardsToAllPlayers(deck);
        dealerTurn.dealInitialCards(deck);
        playerTurnManager.runPlayerTurn(deck);
        dealerTurn.runDealerTurn(deck);

        BettingResult bettingResult = new BettingResult(playerTurnManager.getPlayersBet());
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
