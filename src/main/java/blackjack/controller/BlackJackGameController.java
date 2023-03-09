package blackjack.controller;

import blackjack.domain.*;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.DeckType;
import blackjack.domain.gameplayer.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGameController {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = start();
        play(game);
        finish(game);
    }

    private Game start() {
        Game game = initGame();
        printStartCards(game);
        return game;
    }

    private Game initGame() {
        Deck deck = initDeck();
        return new Game(deck, new Dealer(), new Players(initPlayers()));
    }

    private List<Player> initPlayers() {
        try {
            List<String> playersName = inputView.readPlayersName();
            return generatePlayers(playersName);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]" + e.getMessage());
            return initPlayers();
        }
    }

    private List<Player> generatePlayers(List<String> playersName) {
        return playersName.stream()
                .map(name -> new Player(new Name(name)))
                .collect(Collectors.toList());
    }

    private Deck initDeck() {
        return DeckFactory.createDeck(DeckType.BLACKJACK);
    }

    private void printStartCards(Game game) {
        outputView.printStartMsg(game.showPlayersName());
        outputView.printDealerCards(game.showDealerCards(), System.lineSeparator());
        for (Player player : game.getPlayers()) {
            outputView.printPlayerCards(player, LINE_SEPARATOR);
        }
    }

    private void play(Game game) {
        for (Player player : game.getPlayers()) {
            playerTurn(game, player);
        }
        dealerTurn(game);
    }

    private void playerTurn(Game game, Player player) {
        while (isCheckPlayerCommand(player)) {
            game.giveCardTo(player);
            outputView.printPlayerCards(player, LINE_SEPARATOR);
        }
    }

    private boolean isCheckPlayerCommand(Player player) {
        try {
            return player.canContinue() && isCommandHit(player);
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return isCheckPlayerCommand(player);
        }
    }

    private boolean isCommandHit(Player player) {
        String userCommand = inputView.readTryCommand(player.showName());
        return Command.isHit(userCommand);
    }

    private void dealerTurn(Game game) {
        while (game.canContinueDealer()) {
            outputView.printDealerHit();
            game.giveCardToDealer();
        }
    }

    private void finish(Game game) {
        outputView.printDealerResult(game.showDealerAllCards(), game.getDealerScore().getScore());

        for (Player player : game.getPlayers()) {
            printOnePlayerResult(player);
        }

        GameResult gameResult = new GameResult(game);
        outputView.printEndMsg();
        outputView.printDealerWinningResult(gameResult.getDealerResult());
        outputView.printPlayerWinningResult(gameResult.getPlayerResult());
    }

    private void printOnePlayerResult(Player player) {
        String playerName = player.showName();
        List<Card> playerCards = player.showCards();
        Score score = player.calculateScore();
        outputView.printPlayerResult(playerName, playerCards, score.getScore());
    }
}
