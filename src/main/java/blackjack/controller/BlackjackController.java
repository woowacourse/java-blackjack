package blackjack.controller;

import blackjack.domain.blackjackgame.BlackjackGame;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(createPlayers());

        blackjackGame.start();
        printHandOutResult(blackjackGame.getDealer(), blackjackGame.getPlayers());
        while (blackjackGame.isNotEnd()) {
            Player player = blackjackGame.getCurrentPlayer();
            blackjackGame.drawCurrentPlayer(InputView.getWhetherDrawCard(player));
            OutputView.printPlayerCards(player);
        }
        printGameResult(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    private List<Player> createPlayers() {
        List<String> names = InputView.getPlayerNames();
        return names.stream()
            .map(Player::new)
            .collect(Collectors.toList());
    }

    private void printHandOutResult(Dealer dealer, List<Player> players) {
        OutputView.printHandOutCardsMessage(dealer, players);
        OutputView.printDealerCards(dealer);
        players.forEach(OutputView::printPlayerCards);
        OutputView.printNewLine();
    }

    private void printGameResult(Dealer dealer, List<Player> players) {
        OutputView.printDealerCardsScore(dealer);
        players.forEach(OutputView::printPlayerCardsScore);
        OutputView.printGameResult(players, dealer);
    }

}
