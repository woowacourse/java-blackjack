package controller;

import java.util.List;
import java.util.Map;
import model.Dealer;
import model.Deck;
import model.GameResult;
import model.Judge;
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
        players.startDeal(deck);
        dealer.dealInitialCards(deck);
        OutputView.printInitialDealResult(dealer, players);

        for (Player player : players.getPlayers()) {
            boolean flag = true;
            while ((flag == InputView.readHit(player))) {
                player.receiveCard(deck.pick());
                OutputView.printHitResult(player);
                if (player.isBurst()) {
                    break;
                }; //TODO : 수정은 나중에
            }
        }
        boolean flag = true;
        while (flag == dealer.checkScoreUnderSixteen()){
             OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }

        OutputView.printFinalScore(dealer, players);

        printWinningResultV1(players, dealer);
    }

    private static void printWinningResultV2(Players players, Dealer dealer) {
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<GameResult, Integer> dealerWinningResult = participantWinningResult.decideDealerWinning();

        OutputView.printDealerFinalResult(dealerWinningResult);
        OutputView.printPlayerFinalResultV2(participantWinningResult);
    }

    private static void printWinningResultV1(Players players, Dealer dealer) {
        Judge judge = new Judge();
        Map<Player, GameResult> playerWinning = judge.decidePlayerWinning(players, dealer);
        Map<GameResult, Integer> dealerWinning = judge.decideDealerWinning(playerWinning);

        OutputView.printDealerFinalResult(dealerWinning);
        OutputView.printPlayerFinalResult(playerWinning);
    }
}
