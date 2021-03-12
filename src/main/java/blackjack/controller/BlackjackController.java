package blackjack.controller;

import blackjack.domain.blackjackgame.Money;
import blackjack.domain.blackjackgame.BlackjackGame;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
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
            Player player = blackjackGame.currentPlayer();
            blackjackGame.drawCurrentPlayer(InputView.getWhetherDrawCard(player));
            OutputView.printPlayerCards(player);
        }
         printGameResult(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    private Players createPlayers() {
        List<String> names = InputView.getPlayerNames();
        List<Player> players = names.stream()
            .map(Player::new)
            .collect(Collectors.toList());
        for (Player player : players) {
            player.addMoney(new Money(InputView.getBetMoney(player)));
        }
        return new Players(players);
    }

    private void printHandOutResult(Dealer dealer, Players players) {
        OutputView.printHandOutCardsMessage(dealer, players);
        OutputView.printDealerCards(dealer);
        OutputView.printPlayersCards(players);
        OutputView.printNewLine();
    }

    private void printGameResult(Dealer dealer, Players players) {
        OutputView.printDealerCardsScore(dealer);
        OutputView.printPlayersCardsScore(players);
        OutputView.printGameProfitResult(players, dealer);
    }

}
