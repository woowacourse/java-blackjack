package controller;

import java.util.List;
import java.util.Locale;
import model.Dealer;
import model.Deck;
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

        OutputView.printFinalResult(dealer, players);
    }
}
