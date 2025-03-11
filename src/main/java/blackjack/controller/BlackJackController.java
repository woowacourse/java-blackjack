package blackjack.controller;

import blackjack.model.game.BlackJackGame;
import blackjack.model.game.Result;
import blackjack.model.player.BlackJackPlayer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    private final BlackJackGame blackJackGame;

    public BlackJackController(
            final InputView inputView,
            final OutputView outputView,
            final BlackJackGame blackJackGame
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGame = blackJackGame;
    }

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = makeUsers();
        dealInitialCards(players, dealer);
        outputView.printDealInitialCardsResult(dealer, players);

        players.forEach(this::userDrawMoreCards);
        outputView.printDealerDrawnMoreCards(blackJackGame.drawMoreCard(dealer));

        outputView.printOptimalPoints(dealer, players);

        outputView.printGameResult(
                Result.evaluateDealerResults(dealer, players),
                getUsersResults(dealer, players)
        );
    }

    private List<Player> makeUsers() {
        return inputView.readUserNames()
                .stream()
                .map(Player::new)
                .toList();
    }

    private void userDrawMoreCards(final Player player) {
        while (player.canDrawMoreCard() && inputView.readUserDrawMoreCard(player)) {
            blackJackGame.drawMoreCard(player);
            outputView.printPlayerCards(player);
        }
    }

    private void dealInitialCards(final List<Player> players, final BlackJackPlayer dealer) {
        List<BlackJackPlayer> allBlackJackPlayers = new ArrayList<>(players);
        allBlackJackPlayers.add(dealer);
        blackJackGame.dealInitialCards(allBlackJackPlayers);
    }

    private Map<Player, Result> getUsersResults(final Dealer dealer, final List<Player> players) {
        return players.stream()
                .collect(
                        Collectors.toMap(
                                user -> user,
                                user -> Result.evaluateUserResult(dealer, user)
                        )
                );
    }
}
