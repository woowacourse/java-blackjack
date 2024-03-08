package blackjack.controller;

import static blackjack.view.InputView.DEALER_NAME;

import blackjack.domain.GameBoard;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Players players = createPlayers();
        Player dealer = new Player(new Name(DEALER_NAME));
        GameBoard gameBoard = new GameBoard(dealer, players);
        play(gameBoard);
    }

    private Players createPlayers() {
        List<String> names = inputView.readNames();
        List<Player> playerList = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        return new Players(playerList);
    }

    private void play(GameBoard gameBoard) {
        startSetting(gameBoard);
        proceedPlayerTurn(gameBoard);
        proceedDealerTurn(gameBoard);
        handleResult(gameBoard);
        handleVictory(gameBoard);
    }

    private void startSetting(GameBoard gameBoard) {
        gameBoard.distributeInitialDecks();
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
