package controller;

import static view.InputView.DEALER_NAME;

import domain.GameBoard;
import domain.Name;
import domain.Player;
import domain.Players;
import dto.PlayerDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        List<Player> playerList = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        Players players = new Players(playerList);
        Player dealer = new Player(new Name(DEALER_NAME));
        GameBoard gameBoard = new GameBoard(dealer, players);

        play(gameBoard);
    }

    private void play(GameBoard gameBoard) {
        startSetting(gameBoard);
        proceedPlayerTurn(gameBoard);
        proceedDealerTurn(gameBoard);
        handleResult(gameBoard);
        handleVictory(gameBoard);
    }

    private void startSetting(GameBoard gameBoard) {
        gameBoard.initialDistribute();
        Player dealer = gameBoard.getDealer();
        PlayerDto dealerDto = PlayerDto.from(dealer);
        List<PlayerDto> playersDto = gameBoard.getPlayers()
                .getPlayers()
                .stream()
                .map(PlayerDto::from)
                .toList();
        outputView.printSetting(dealerDto, playersDto);
        outputView.printNewLine();
    }

    private void proceedPlayerTurn(GameBoard gameBoard) {
        for (int playerIndex = 0; playerIndex < gameBoard.countPlayers(); playerIndex++) {
            proceedOnePlayerTurn(gameBoard, playerIndex);
        }
    }

    private void proceedDealerTurn(GameBoard gameBoard) {
        outputView.printNewLine();
        while (gameBoard.isDealerNotOver()) {
            gameBoard.addCardToDealer();
            outputView.printDealerOneMoreCard();
        }
        outputView.printNewLine();
    }

    private void proceedOnePlayerTurn(GameBoard gameBoard, int playerIndex) {
        while (gameBoard.isPlayerNotOver(playerIndex) &&
                inputView.readCommand(gameBoard.getPlayerName(playerIndex).getName())) {
            gameBoard.addCardToPlayer(playerIndex);
            Player player = gameBoard.getPlayer(playerIndex);
            outputView.printCurrentCard(PlayerDto.from(player));
            outputView.printNewLine();
        }
    }

    private void handleResult(GameBoard gameBoard) {
        Player dealer = gameBoard.getDealer();
        PlayerDto dealerDto = PlayerDto.from(dealer);
        List<PlayerDto> playersDto = gameBoard.getPlayers()
                .getPlayers()
                .stream()
                .map(PlayerDto::from)
                .toList();
        outputView.printScoreResult(dealerDto, playersDto);
    }

    private void handleVictory(GameBoard gameBoard) {
        Map<Player, Boolean> playerVictory = gameBoard.calculateVictory();
        Map<String, Boolean> playerNameVictory = new LinkedHashMap<>();
        playerVictory.forEach(
                (key, value) -> playerNameVictory.put(key.getName().getName(), playerVictory.get(key)));
        outputView.printResult(playerNameVictory);
    }
}
