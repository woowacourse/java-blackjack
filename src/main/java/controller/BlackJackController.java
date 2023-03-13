package controller;

import domain.HitCommand;
import domain.card.Hand;
import domain.deck.RandomDeckGenerator;
import domain.dto.CardNames;
import domain.game.Blackjack;
import domain.game.Result;
import domain.name.Names;
import domain.user.Player;
import domain.user.Users;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private Blackjack blackJack;

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
        Users users = Users.from(initPlayerNames());

        blackJack = Blackjack.of(users, new RandomDeckGenerator().generateDeck());
        printInitMessages(users.getPlayerNames());
    }

    private Names initPlayerNames() {
        Names playerNames = Names.of(InputView.askPlayerNames());
        return playerNames;
    }

    private void printInitMessages(final List<String> playerNames) {
        OutputView.printInitHitMessage(playerNames);
        OutputView.printDealerCardWithHidden(blackJack.getDealerCardWithHidden());
        OutputView.printPlayerCards(blackJack.getPlayerToCardNames());
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
        while (isHittablePlayer(player)) {
            blackJack.giveCard(playerName);
            Hand playerHand = player.getHand();
            OutputView.printEachPlayerCards(playerName, new CardNames(playerHand.getCardNames()));
        }
    }

    private boolean isHittablePlayer(final Player player) {
        return blackJack.isHittablePlayer(player) && askHitCommandToPlayer(player).isHit();
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
        OutputView.printDealerCardWithScore(blackJack.getDealerCardNames(), blackJack.getDealerScore());
        OutputView.printPlayerCardWithScore(blackJack.getPlayerToCardNames(), blackJack.getPlayerToScore());
        Map<String, Result> playerTotalResults = blackJack.calculateTotalPlayerResults();
        OutputView.printGameResult(blackJack.calculateTotalDealerResult(playerTotalResults), playerTotalResults);
    }
}
