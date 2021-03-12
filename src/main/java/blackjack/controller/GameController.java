package blackjack.Controller;

import blackjack.domain.Game;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class GameController {
    public void run() {
        Game game = initializeGame();
        printInitialCardsInfo(game);
        doPlayersTurn(game);
        doDealerTurn(game);
        printGameResult(game);
    }

    private Game initializeGame() {
        Players players = createPlayers();
        return Game.of(players);
    }

    private Players createPlayers() {
        Map<String, Integer> playerInformation = getPlayerNameAndBattingMoney();
        List<Player> players = playerInformation.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new Players(players);
    }

    private Map<String, Integer> getPlayerNameAndBattingMoney() {
        List<String> inputNames = InputView.inputNames();

        return inputNames.stream()
                .map(String::trim)
                .collect(toMap(name -> name, this::inputBattingMoney));
    }

    private int inputBattingMoney(String name) {
        return InputView.inputBattingMoney(name);
    }

    private void printInitialCardsInfo(Game game) {
        OutputView.printInitialCardsInfo(game.getDealer(), game.getPlayers());
    }


    private void doPlayersTurn(Game game) {
        for (Player player : game.getPlayers()) {
            doEachPlayerTurn(game, player);
        }
    }

    private void doEachPlayerTurn(Game game, Player player) {
        if (!player.isDrawable()) {
            return;
        }
        if (InputView.inputKeepDrawReply(player)) {
            game.giveCardToPlayer(player);
            OutputView.printParticipantCardsInfo(player);
            doEachPlayerTurn(game, player);
            return;
        }
        player.stay();
    }

    private void doDealerTurn(Game game) {
        Dealer dealer = game.getDealer();
        while (dealer.isDrawable()) {
            game.giveCardToDealer(dealer);
            OutputView.printDealerDrawMessage();
        }
    }

    private void printGameResult(Game game) {
        Map<String, Integer> playersProfitResult = game.getPlayersProfitResult();
        Map<String, Integer> dealerProfitResult = game.getDealerProfitResult(playersProfitResult);

        OutputView.printDealerAndPlayersCardsInfoWithScore(game.getDealer(), game.getPlayers());
        OutputView.printDealerAndPlayersProfitResult(dealerProfitResult, playersProfitResult);
    }
}
