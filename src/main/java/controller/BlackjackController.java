package controller;

import java.util.List;
import java.util.Map;
import model.Dealer;
import model.Deck;
import model.GameResult;
import model.Player;
import model.ParticipantWinningResult;
import model.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            List<String> playerNames = InputView.readPlayerNames();
            Players players = Players.from(playerNames);
            Dealer dealer = new Dealer();
            Deck deck = Deck.of();

            dealInitially(players, deck, dealer);

            hitOrStandAtPlayersTurn(players, deck);
            hitOrStandAtDealerTurn(dealer, deck);

            OutputView.printFinalScore(dealer, players);
            printWinningResult(players, dealer);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private static void hitOrStandAtDealerTurn(Dealer dealer, Deck deck) {
        boolean flag = true;
        while (flag == dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }

    private static void hitOrStandAtPlayersTurn(Players players, Deck deck) {
        players.getPlayers().forEach(player ->
                hitOrStandAtOnePlayerTurn(deck, player)
        );
    }

    private static void hitOrStandAtOnePlayerTurn(Deck deck, Player player) {
        boolean flag = true;
        while ((flag == InputView.readHit(player))) {
            player.receiveCard(deck.pick());
            OutputView.printHitResult(player);
            if (player.isBurst()) {
                break;
            }
        }
    }

    private static void dealInitially(Players players, Deck deck, Dealer dealer) {
        players.dealInitialCards(deck);
        dealer.dealInitialCards(deck);
        OutputView.printInitialDealResult(dealer, players);
    }

    private static void printWinningResult(Players players, Dealer dealer) {
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<GameResult, Integer> dealerWinningResult = participantWinningResult.decideDealerWinning();

        OutputView.printDealerFinalResult(dealerWinningResult);
        OutputView.printPlayerFinalResult(participantWinningResult);
    }
}
