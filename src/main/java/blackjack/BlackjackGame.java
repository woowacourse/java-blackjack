package blackjack;

import blackjack.domain.result.GameScoreBoard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    public static final String DELIMITER = ",";

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        initiallySetCard(dealer, players);
        takeMoreCardPlayerTurn(dealer, players);
        takeMoreCardDealerTurn(dealer);
        OutputView.printParticipantResult(dealer, players);

        GameScoreBoard result = GameScoreBoard.of(dealer, players);
        OutputView.printBlackjackGameResult(result);
    }

    private static List<Player> getPlayers() {
        return Arrays.stream(InputView.inputParticipantsNames().split(DELIMITER))
            .map(playerName -> new Player(playerName.trim()))
            .collect(Collectors.toList());
    }

    private static void initiallySetCard(Dealer dealer, List<Player> players) {
        dealer.drawBaseCardHand();
        dealer.drawCardToPlayers(players);
        OutputView.showParticipantsHand(dealer, players);
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
}
