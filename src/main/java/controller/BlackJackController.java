package controller;

import domain.HitCommand;
import domain.deck.RandomDeckGenerator;
import domain.game.BlackJack;
import domain.game.Result;
import domain.user.Player;
import domain.user.Users;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private BlackJack blackJack;

    public void run() {
        try {
            initGame();
            playGame();
            endGame();
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void initGame() {
        List<String> playerNames = InputView.askPlayerNames();
        Users users = Users.from(playerNames);
        blackJack = BlackJack.of(users, new RandomDeckGenerator().generateDeck());
        OutputView.printInitMessage(playerNames);
        OutputView.printDealerCardWithHidden(blackJack.getDealerCardWithHidden());
        OutputView.printPlayerCards(blackJack.getPlayerToCard());
    }

    private void playGame() {
        playTurnOfPlayers();
        playTurnOfDealer();
    }

    private void playTurnOfPlayers() {
        List<Player> hittablePlayers = blackJack.getHittablePlayers();
        hittablePlayers.forEach(this::playTurnOfPlayer);
    }

    private void playTurnOfPlayer(final Player player) {
        String playerName = player.getName();
        while (blackJack.isHittablePlayer(player) && askHitCommandToPlayer(player).isHit()) {
            blackJack.giveCard(playerName);
            OutputView.printEachPlayerCards(playerName, player.getCards());
        }
    }

    private HitCommand askHitCommandToPlayer(final Player player) {
        String inputCommand = InputView.askHitCommand(player.getName());
        return HitCommand.from(inputCommand);
    }

    private void playTurnOfDealer() {
        while (blackJack.isHittableDealer()) {
            blackJack.giveCardToDealer();
            OutputView.printDealerHitMessage();
        }
    }

    private void endGame() {
        OutputView.printDealerCardWithScore(blackJack.getDealerCards(), blackJack.getDealerScore());
        OutputView.printPlayerCardWithScore(blackJack.getPlayerToCard(), blackJack.getPlayerToScore());
        Map<String, Result> playerTotalResults = blackJack.calculateTotalPlayerResults();
        OutputView.printGameResult(blackJack.calculateTotalDealerResult(playerTotalResults), playerTotalResults);
    }
}
