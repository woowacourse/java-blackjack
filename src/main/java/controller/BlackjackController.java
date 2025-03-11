package controller;

import java.util.List;
import java.util.Map;
import model.BettingResult;
import model.participant.Dealer;
import model.card.Deck;
import model.participant.Player;
import model.ParticipantWinningResult;
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
        Deck deck = Deck.of();

        dealInitially(players, deck, dealer);
        OutputView.printInitialDealResult(dealer, players);

        hitOrStandAtPlayersTurn(players, deck);
        hitOrStandAtDealerTurn(dealer, deck);
        OutputView.printFinalScore(dealer, players);

        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        bettingResult.calculatePlayerBettingResult(players, participantWinningResult);
        printFinalGameResult(bettingResult);
    }

    private void dealInitially(Players players, Deck deck, Dealer dealer) {
        players.dealInitialCards(deck);
        dealer.dealInitialCards(deck);
    }

    private void hitOrStandAtPlayersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitOrStandAtOnePlayerTurn(deck, player);
        }
    }

    private void hitOrStandAtOnePlayerTurn(Deck deck, Player player) {
        boolean flag = true;
        while (flag == InputView.readHit(player)) {
            player.receiveCard(deck.pick());
            OutputView.printHitResult(player);
            if (player.isBust()) {
                break;
            }
        }
    }

    private void hitOrStandAtDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }

    private void printFinalGameResult(BettingResult bettingResult) {
        OutputView.printDealerFinalResult(bettingResult);
        OutputView.printPlayerFinalResult(bettingResult);
    }
}
