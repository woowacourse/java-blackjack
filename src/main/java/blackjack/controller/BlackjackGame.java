package blackjack.controller;

import blackjack.domain.GameBoard;
import blackjack.domain.participants.GamblingMoney;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.dto.BettingMoneyDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Players players = createPlayers();
        GameBoard gameBoard = new GameBoard(players);
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
        startBetting(gameBoard);
        startSetting(gameBoard);
        proceedPlayerTurn(gameBoard);
        proceedDealerTurn(gameBoard);
        handleResult(gameBoard);
        handleBettingMoney(gameBoard);
    }

    private void startBetting(GameBoard gameBoard) {
        Players players = gameBoard.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            int money = inputView.readMoney(players.getPlayerName(i).getValue());
            players.betPlayerMoney(new GamblingMoney(money), i);
        }
    }

    private void startSetting(GameBoard gameBoard) {
        gameBoard.distributeInitialHand();
        PlayerDto dealerDto = PlayerDto.from(gameBoard.getDealer());
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
                inputView.readCommand(gameBoard.getPlayerName(playerIndex).getValue())) {
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

    private void handleBettingMoney(GameBoard gameBoard) {
        gameBoard.calculateBettingMoney();
        BettingMoneyDto bettingMoneyResult = BettingMoneyDto.from(gameBoard.getDealer(), gameBoard.getPlayers());
        outputView.printMoneyResult(bettingMoneyResult);
    }
}
