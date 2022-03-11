package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.service.GameScoreDecider;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        initiallySetCard(dealer, players);

        takeMoreCardPlayerTurnForAllPlayers(dealer, players);
        takeMoreCardDealerTurn(dealer);

        OutputView.printParticipantScore(dealer, players);

        GameScoreDecider gameScoreDecider = new GameScoreDecider(dealer, players);

        OutputView.printBlackjackGameResult(dealer, players);
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

    private static void takeMoreCardPlayerTurnForAllPlayers(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            takeMoreCardPlayerTurnForPlayer(dealer, player);
        }
    }

    private static void takeMoreCardPlayerTurnForPlayer(Dealer dealer, Player player) {
        String takeCardAnswer = "";
        do {
            takeCardAnswer = InputView.inputOneMoreCard(player);
            dealer.giveCard(player);
            OutputView.showPlayerHand(player);
        } while (isGoingTakeMoreCard(takeCardAnswer, player));
    }

    private static boolean isGoingTakeMoreCard(String takeCardAnswer, Player player) {
        return takeCardAnswer.equalsIgnoreCase("Y") && !player.isBust();
    }

    private static void takeMoreCardDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerHandDrawMessage();
            dealer.selfDraw();
        }
    }
}
