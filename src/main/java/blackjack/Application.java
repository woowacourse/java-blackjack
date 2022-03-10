package blackjack;

import blackjack.domain.GameScoreBoard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(final String... args) {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        initiallySetCard(dealer, players);
        OutputView.showParticipantsHand(dealer, players);

        takeMoreCardPlayerTurn(dealer, players);
        takeMoreCardDealerTurn(dealer);
        OutputView.printParticipantResult(dealer, players);

        GameScoreBoard result = new GameScoreBoard(dealer, players);
        OutputView.printBlackjackGameResult(result);
    }

    private static void initiallySetCard(Dealer dealer, List<Player> players) {
        dealer.drawBaseCardHand();
        dealer.drawCardToPlayers(players);
    }

    private static void takeMoreCardPlayerTurn(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            playerTurn(dealer, player);
        }
    }

    private static void playerTurn(Dealer dealer, Player player) {
        while (!player.isBust() && InputView.inputOneMoreCard(player)) {
            dealer.giveCard(player);
            OutputView.showPlayerHand(player);
        }
        if (player.isBust()) {
            OutputView.printBustMessage();
            return;
        }
        OutputView.showPlayerHand(player);
    }

    private static void takeMoreCardDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerHandDrawMessage();
            dealer.selfDraw();
        }
    }

    private static List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        String participantsNames = InputView.inputParticipantsNames();
        for (String participantName : participantsNames.split(",")) {
            players.add(new Player(participantName));
        }
        return players;
    }
}
