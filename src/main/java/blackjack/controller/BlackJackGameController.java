package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.DeckType;
import blackjack.domain.game.Game;
import blackjack.domain.game.GameResult;
import blackjack.domain.gameplayer.*;
import blackjack.utils.Command;
import blackjack.utils.LogType;
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
        Game game = init();
        play(game);
        finish(game);
    }

    private Game init() {
        Deck deck = initDeck();
        List<Name> names = initNames();
        List<Player> players = initPlayers(names);
        Game game = new Game(deck, new Dealer(), new Players(players));

        outputView.printStart(game.getPlayers(), game.getDealer());
        return game;
    }

    private Deck initDeck() {
        return DeckFactory.createDeck(DeckType.BLACKJACK);
    }

    private List<Name> initNames() {
        try {
            List<String> playersName = inputView.readPlayersName();
            return generateName(playersName);
        } catch (IllegalArgumentException exception) {
            LogType.ERROR_MESSAGE.log(exception.getMessage());
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
            return new Player(name, new Betting(betting));
        } catch (IllegalArgumentException exception) {
            LogType.ERROR_MESSAGE.log(exception.getMessage());
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
            outputView.printPlayerWithCards(player, LINE_SEPARATOR);
        }
    }

    private boolean isCheckPlayerCommand(Player player) {
        try {
            return player.canContinue() && isCommandHit(player);
        } catch (IllegalArgumentException exception) {
            LogType.ERROR_MESSAGE.log(exception.getMessage());
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
        Dealer dealer = game.getDealer();
        Players players = game.getPlayers();
        outputView.printGameResult(dealer, players);

        GameResult gameResult = game.createGameResult();
        outputView.printBettingResult(gameResult.getDealerBettingResults(), gameResult.getPlayerBettingResults());
    }
}
