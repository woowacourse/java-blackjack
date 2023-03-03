package controller;

import domain.DrawCommand;
import domain.card.Card;
import domain.card.Cards;
import domain.game.BlackJackGame;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;
import domain.user.PlayerStatus;
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

    private void playGame(BlackJackGame blackJackGame) {
        progressPlayersTurn(blackJackGame);
        progressDealerTurn(blackJackGame);
        showUsersCardResult(blackJackGame);
        blackJackGame.judgeWinner();
        showFinalResult(blackJackGame);
    }

    private void showFinalResult(BlackJackGame blackJackGame) {
        Map<Boolean, Integer> dealerWinningRecord = blackJackGame.getDealer().getWinningRecord();
        Map<String, Boolean> userFinalResult = blackJackGame.getUserFinalResult();
        outputView.printFinalResult(dealerWinningRecord, userFinalResult);
    }

    private void showUsersCardResult(BlackJackGame blackJackGame) {
        Map<User, List<Card>> userResult = new LinkedHashMap<>();
        userResult.put(blackJackGame.getDealer(), blackJackGame.getDealer().getCards());
        blackJackGame.getPlayers().forEach(player -> userResult.put(player, player.getCards()));
        outputView.printUsersCardResult(userResult);
    }

    private void progressPlayersTurn(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for(Player player : players) {
            progressPlayerTurn(blackJackGame, player);
        }
    }

    private void progressPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while(player.getStatus().equals(PlayerStatus.NORMAL) && readDrawCommand(player).equals(DrawCommand.DRAW)) {
            blackJackGame.drawOneMoreCardForPlayer(player);
            showDrawResult(player);
        }
        if(player.getStatus().equals(PlayerStatus.NORMAL)) {
            showDrawResult(player);
        }
    }

    private void progressDealerTurn(BlackJackGame blackJackGame) {
        blackJackGame.drawCardUntilOverSixteen();

        Dealer dealer = blackJackGame.getDealer();
        int dealerDrawCount = dealer.getCards().size() - 2;

        if (dealerDrawCount > 0) {
            outputView.printDealerDrawResult(dealerDrawCount);
        }
    }

    private BlackJackGame setUp() {
        Cards cards = new Cards();
        Dealer dealer = new Dealer(cards.drawForFirstTurn());
        List<Player> players = setUpPlayers(cards);
        showSetUpResult(dealer, players);
        return new BlackJackGame(players, dealer, cards);
    }

    private List<Player> setUpPlayers(Cards cards) {
        return readUsersName().stream()
                .map(name -> new Player(new Name(name), cards.drawForFirstTurn()))
                .collect(Collectors.toList());
    }

    private void showSetUpResult(Dealer dealer, List<Player> players) {
        HashMap<String, List<Card>> setUpResult = new LinkedHashMap<>();
        setUpResult.put("딜러", dealer.getCards().subList(0,1));
        players.forEach(player -> setUpResult.put(player.getName(), player.getCards()));
        outputView.printSetUpResult(setUpResult);
    }

    private void showDrawResult(Player player) {
        outputView.printPlayerDrawResult(player.getName(), player.getCards());
    }

    private List<String> readUsersName() {
        outputView.printInputPlayerNameMessage();
        return inputView.readPlayersName();
    }

    private DrawCommand readDrawCommand(Player player) {
        outputView.printAskOneMoreCardMessage(player.getName());
        return inputView.readDrawCommand();
    }
}
