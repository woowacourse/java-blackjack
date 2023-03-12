package controller;

import domain.command.DrawCommand;
import domain.card.Card;
import domain.card.GameDeck;
import domain.card.ShuffleDeckGenerator;
import domain.game.BlackJackGame;
import domain.game.GameResult;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Names;
import domain.user.Player;
import domain.state.PlayerStatus;
import domain.user.User;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = setUp();
        playGame(blackJackGame);
    }

    private BlackJackGame setUp() {
        GameDeck gameDeck = new GameDeck(new ShuffleDeckGenerator());
        Dealer dealer = new Dealer(gameDeck.drawForFirstTurn());
        List<Player> players = setUpPlayers(gameDeck);
        showSetUpResult(dealer, players);
        return new BlackJackGame(players, dealer, gameDeck);
    }

    private List<Player> setUpPlayers(GameDeck gameDeck) {
        return readUsersName().stream()
                .map(name -> new Player(name, gameDeck.drawForFirstTurn()))
                .collect(Collectors.toList());
    }

    private List<Name> readUsersName() {
        outputView.printInputPlayerNameMessage();
        Names names = new Names(inputView.readPlayersName());
        return names.getNames();
    }

    private void showSetUpResult(Dealer dealer, List<Player> players) {
        HashMap<String, List<Card>> setUpResult = new LinkedHashMap<>();
        setUpResult.put("딜러", dealer.getCards().subList(0, 1));
        players.forEach(player -> setUpResult.put(player.getName(), player.getCards()));
        outputView.printSetUpResult(setUpResult);
    }

    private void playGame(BlackJackGame blackJackGame) {
        progressPlayersTurn(blackJackGame);
        progressDealerTurn(blackJackGame);
        showUsersCardResult(blackJackGame);
        GameResult gameResult = new GameResult(blackJackGame.getPlayerNames());
        gameResult.saveResults(blackJackGame.getDealer(), blackJackGame.getPlayers());
        showFinalResult(gameResult);
    }

    private void progressPlayersTurn(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            progressPlayerTurn(blackJackGame, player);
        }
    }

    private void progressPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (player.isUserStatus(PlayerStatus.NORMAL) && readDrawCommand(player).equals(DrawCommand.DRAW)) {
            blackJackGame.drawOneMoreCardForPlayer(player);
            showDrawResult(player);
        }
        if (player.isUserStatus(PlayerStatus.NORMAL)) {
            showDrawResult(player);
        }
    }

    private DrawCommand readDrawCommand(Player player) {
        outputView.printAskOneMoreCardMessage(player.getName());
        return inputView.readDrawCommand();
    }

    private void showDrawResult(Player player) {
        outputView.printPlayerDrawResult(player.getName(), player.getCards());
    }

    private void progressDealerTurn(BlackJackGame blackJackGame) {
        blackJackGame.drawCardUntilOverSixteen();

        Dealer dealer = blackJackGame.getDealer();
        int dealerDrawCount = dealer.getCards().size() - 2;

        if (dealerDrawCount > 0) {
            outputView.printDealerDrawResult(dealerDrawCount);
        }
    }

    private void showUsersCardResult(BlackJackGame blackJackGame) {
        Map<User, List<Card>> userResult = new LinkedHashMap<>();
        userResult.put(blackJackGame.getDealer(), blackJackGame.getDealer().getCards());
        blackJackGame.getPlayers().forEach(player -> userResult.put(player, player.getCards()));
        outputView.printUsersCardResult(userResult);
    }

    private void showFinalResult(GameResult gameResult) {
        outputView.printFinalResult(gameResult.getDealerResult(), gameResult.getPlayerResults());
    }
}
