package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Dealer;
import model.Deck;
import model.GameResult;
import model.Judge;
import model.Player;
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
                if (player.getParticipantHand().checkBurst()) {
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

        Judge judge = new Judge();
        Map<GameResult, Integer> dealerWinning = judge.decideDealerWinning(dealer, players);

        OutputView.printFinalResult(dealerWinning);

        for (Player player : players.getPlayers()) {
            GameResult playerResult = judge.checkPlayerWin(dealer, player);
            OutputView.printPlayerFinalResult(player, playerResult);
        }
    }
}
