package blackjack.controller;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.deck.Deck;
import blackjack.domain.game.Game;
import blackjack.domain.game.GameResults;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.rule.HitCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = makeGameReady();
        Players players = createPlayers();

        game.initializeDealToParticipants(players);
        outputView.printFirstCardStatus(game.getDealer(), players);

        turnToPlayers(game, players);
        turnToDealer(game);

        printResults(game, players);
    }

    private void printResults(Game game, Players players) {
        GameResults gameResults = GameResults.of(game.getDealer(), players);
        outputView.printScoreResult(game.getDealer(), players);
        outputView.printGameResultProfit(gameResults);
    }

    private static Game makeGameReady() {
        return new Game(new Deck(), new Dealer());
    }

    private Players createPlayers() {
        List<String> playerNames = inputView.readNames();
        List<Player> players = playerNames.stream()
                .map(name -> new Player(new Name(name), new BettingAmount(inputView.readBettingAmount(name))))
                .toList();
        return new Players(players);
    }

    private void turnToDealer(Game game) {
        while (game.isDealerTurn()) {
            outputView.printDealerReceiveCard();
            game.hitDealer();
            if (game.isDealerBust()) {
                outputView.printBust("딜러");
                return;
            }
        }
    }

    private void turnToPlayers(Game game, Players players) {
        for (Player player : players.getPlayers()) {
            turnToOnePlayer(game, player);
        }
    }

    private void turnToOnePlayer(Game game, Player player) {
        while (player.canReceive()) {
            HitCommand command = HitCommand.from(inputView.readReceiveCard(player));
            if (!command.isHit()) {
                return;
            }

            game.hitTo(player);
            if (player.isBust()) {
                outputView.printBust(player.getName());
                return;
            }
            outputView.printPlayerCardStatus(player, player.getCards());
        }
    }
}
