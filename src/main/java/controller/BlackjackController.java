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
        List<String> playerNames = InputView.readPlayerNames();
        Players players = Players.from(playerNames);
        Dealer dealer = new Dealer();
        Deck deck = Deck.of();

        dealInitially(players, deck, dealer);

        hitOrStandAtPlayersTurn(players, deck);
        hitOrStandAtDealerTurn(dealer, deck);

        OutputView.printFinalScore(dealer, players);
        printWinningResult(players, dealer);
    }

    private void dealInitially(Players players, Deck deck, Dealer dealer) {
        players.dealInitialCards(deck);
        dealer.dealInitialCards(deck);
        OutputView.printInitialDealResult(dealer, players);
    }

    private void hitOrStandAtPlayersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitOrStandAtOnePlayerTurn(deck, player);
        }
    }

    private void hitOrStandAtOnePlayerTurn(Deck deck, Player player) {
        boolean flag = true;
        while ((flag == InputView.readHit(player))) {
            player.receiveCard(deck.pick());
            OutputView.printHitResult(player);
            if (player.isBurst()) {
                break;
            }
        }
    }

    private void hitOrStandAtDealerTurn(Dealer dealer, Deck deck) {
        boolean flag = true;
        while (flag == dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }

    private void printWinningResult(Players players, Dealer dealer) {
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<GameResult, Integer> dealerWinningResult = participantWinningResult.decideDealerWinning();

        OutputView.printDealerFinalResult(dealerWinningResult);
        OutputView.printPlayerFinalResult(participantWinningResult);
    }
}
