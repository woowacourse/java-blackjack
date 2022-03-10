package blackjack.controller;

import blackjack.domain.GameScoreBoard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        initiallySetCard(dealer, players);

        takeMoreCardPlayerTurn(dealer, players);
        takeMoreCardDealerTurn(dealer);

        OutputView.printParticipantScore(dealer, players);

        GameScoreBoard result = new GameScoreBoard(dealer, players);
        OutputView.printBlackjackGameResult(result);
    }

    private static List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        String participantsNames = InputView.inputParticipantsNames();
        for (String participantName : participantsNames.split(",")) {
            players.add(new Player(participantName));
        }
        return players;
    }

    private static void initiallySetCard(Dealer dealer, List<Player> players) {
        dealer.drawCardHandFirstTurn();
        dealer.drawCardToPlayers(players);
        OutputView.showParticipantsHand(dealer, players);
    }

    private static void takeMoreCardPlayerTurn(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            String takeCardAnswer = InputView.inputOneMoreCard(player);

            if (takeCardAnswer.equalsIgnoreCase("Y")) {
                do {
                    dealer.giveCard(player);
                    OutputView.showPlayerHand(player);
                    if (player.isBust()) {
                        break;
                    }
                    takeCardAnswer = InputView.inputOneMoreCard(player);
                } while (takeCardAnswer.equalsIgnoreCase("Y"));
            }

            if (takeCardAnswer.equalsIgnoreCase("N")) {
                OutputView.showPlayerHand(player);
            }
        }
    }

    private static void takeMoreCardDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerHandDrawMessage();
            dealer.selfDraw();
        }
    }
}
