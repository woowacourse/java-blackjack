package blackjack.controller;

import blackjack.domain.Command;
import blackjack.domain.Game;
import blackjack.domain.GameResult;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.DeckType;
import blackjack.domain.gameplayer.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
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
        outputView.printStart(game.getPlayers(), game.getDealer());
        return game;
    }

    private Game initGame() {
        Deck deck = initDeck();
        List<Name> names = initNames();
        return new Game(deck, new Dealer(), new Players(initPlayers(names)));
    }

    private Deck initDeck() {
        return DeckFactory.createDeck(DeckType.BLACKJACK);
    }

    private List<Name> initNames() {
        try {
            List<String> playersName = inputView.readPlayersName();
            return generateName(playersName);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]" + e.getMessage());
            return initNames();
        }
    }

    private List<Name> generateName(List<String> playersName) {
        return playersName.stream()
                .map(name -> new Name(name))
                .collect(Collectors.toList());
    }

    private List<Player> initPlayers(List<Name> names) {
        List<Player> players = new ArrayList<>();
        for (Name name : names) {
            Player player = generatePlayer(name);
            players.add(player);
        }
        return players;
    }

    private Player generatePlayer(Name name) {
        try {
            int betting = inputView.readBetting(name.getName());
            return new Player(name, Betting.of(betting));
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]" + e.getMessage());
            return generatePlayer(name);
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
            outputView.printPlayerName(player);
            outputView.printCards(player.showCards(), LINE_SEPARATOR);
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
        outputView.printGameResult(game.getDealer(), game.getPlayers());

        GameResult gameResult = new GameResult(game);
        outputView.printBettingResult(gameResult.getDealerBettingResults(), gameResult.getPlayerBettingResults());
    }
}
