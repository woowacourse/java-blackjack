package controller;

import domain.BlackJackGame;
import domain.GameResult;
import domain.TurnAction;
import domain.board.PlayerBoards;
import domain.user.Dealer;
import domain.user.Player;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class GameController {

    private final BlackJackGame blackJackGame;

    private GameController(BlackJackGame blackJackGame) {
        this.blackJackGame = blackJackGame;
    }

    public static GameController makeWithInput() {
        try {
            String playerNamesInput = InputView.getParticipantNames();
            final String nameDelimiter = ",";
            List<String> playerNames = Arrays.stream(playerNamesInput.split(nameDelimiter, -1))
                .collect(Collectors.toList());
            PlayerBoards playerBoards = PlayerBoards.of(playerNames);
            return new GameController(BlackJackGame.from(playerBoards));
        } catch (IllegalArgumentException exception) {
            InputView.printErrorMessage(exception);
            return makeWithInput();
        }
    }

    public void ready() {
        blackJackGame.initializeHand();
        OutputView.printReady(blackJackGame.getPlayerNames());
        OutputView.printDealerReadyStatus(blackJackGame.getDealerName(), blackJackGame.getDealerInitialHand());
        OutputView.printPlayersReadyStatus(blackJackGame.getPlayerNames(), blackJackGame.getPlayerInitialHand());
    }

    public void play() {
        playersTurn();
        dealerTurn();
    }

    private void playersTurn() {
        if (blackJackGame.isGameLeft()) {
            playerTurn(blackJackGame.getCurrentPlayerBoard().getPlayer());
        }
        if (blackJackGame.isGameLeft()) {
            playersTurn();
        }
    }

    private void playerTurn(Player player) {
        try {
            String input = InputView.inputNeedMoreCard(player.getName());
            TurnAction action = TurnAction.getActionByInput(input);
            blackJackGame.playerPlay(action);
            OutputView.printPlayerReadyStatus(player.getName(), player.getHand());
        } catch (IllegalArgumentException exception) {
            InputView.printErrorMessage(exception);
            playerTurn(player);
        }
    }

    private void dealerTurn() {
        while (blackJackGame.dealerNeedMoreCard()) {
            OutputView.printDealerReceivedCard();
            blackJackGame.dealerPlay();
        }
    }

    public void printFinalGameResult() {
        printAllStatus(blackJackGame.getDealer(), blackJackGame.getPlayers());
        List<String> playerNames = blackJackGame.getPlayerNames();
        List<GameResult> playersGameResult = blackJackGame.getPlayersGameResult();
        printDealerGameResult(playersGameResult);
        for (int index = 0; index < playerNames.size(); index++) {
            OutputView.printPlayerGameResult(playerNames.get(index), playersGameResult.get(index));
        }
    }

    private void printDealerGameResult(List<GameResult> playersGameResults) {
        long winCount = playersGameResults.stream()
            .filter(playersGameResult -> GameResult.LOSE == playersGameResult).count();
        long loseCount = playersGameResults.stream()
            .filter(playersGameResult -> GameResult.WIN == playersGameResult)
            .count();
        long drawCount = playersGameResults.size() - winCount - loseCount;
        OutputView.printDealerGameResult(winCount, drawCount, loseCount);
    }

    private void printAllStatus(Dealer dealer, List<Player> players) {
        OutputView.printLineSeparator();
        OutputView.printDealerNameAndHandAndPoint(dealer.getName(), dealer.getHand(), dealer.getPoint());
        players.forEach(
            (participant) -> OutputView.printPlayerNameAndHandAndPoint(participant.getName(), participant.getHand(),
                participant.getPoint()));
    }
}
