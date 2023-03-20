package controller;

import domain.card.ShuffleDeckGenerator;
import domain.command.DrawCommand;
import domain.dto.UserDto;
import domain.game.BlackjackGame;
import domain.user.Name;
import domain.user.Players;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = setUpBlackjackGame();
        playGame(blackjackGame);
    }

    private BlackjackGame setUpBlackjackGame() {
        return new BlackjackGame(createPlayers(), new ShuffleDeckGenerator());
    }

    private Players createPlayers() {
        List<String> playerNames = readPlayerNames();
        List<Integer> bets = readPlayerBets(playerNames);

        return new Players(playerNames, bets);
    }

    private List<String> readPlayerNames() {
        outputView.printInputPlayerNameMessage();
        return inputView.readPlayersName();
    }

    private List<Integer> readPlayerBets(List<String> playerNames) {
        return playerNames.stream()
                .map(this::readPlayerBet)
                .collect(Collectors.toList());
    }

    private int readPlayerBet(String name) {
        outputView.printInputPlayerBettingMessage(name);
        return inputView.readPlayerBetting();
    }

    private void playGame(BlackjackGame blackjackGame) {
        showSetUpResult(blackjackGame.getDealerSetUpDto(), blackjackGame.getAllPlayerDtos());
        progressPlayersTurn(blackjackGame);
        progressDealerTurn(blackjackGame);
        showUserCardResults(blackjackGame);
        showFinalProfit(blackjackGame);
    }

    private void showSetUpResult(UserDto dealerSetUpData, List<UserDto> playerGameData) {
        outputView.printSetUpResult(dealerSetUpData, playerGameData);
    }

    private void progressPlayersTurn(BlackjackGame blackjackGame) {
        while (blackjackGame.hasReadyPlayer()) {
            progressPlayerTurn(blackjackGame);
        }
    }

    private void progressPlayerTurn(BlackjackGame blackjackGame) {
        UserDto readyPlayerGameDataDto = blackjackGame.getReadyPlayerDto();
        Name playerName = new Name(readyPlayerGameDataDto.getName());
        while (!blackjackGame.hasPlayerResult(playerName) && isPlayerInputDraw(readyPlayerGameDataDto)) {
            drawCardForPlayer(blackjackGame, playerName);
        }
        if (!blackjackGame.hasPlayerResult(playerName)) {
            doStayForPlayer(blackjackGame, playerName);
        }
    }

    private boolean isPlayerInputDraw(UserDto readyPlayerGameDataDto) {
        outputView.printAskOneMoreCardMessage(readyPlayerGameDataDto);
        DrawCommand inputCommand = inputView.readDrawCommand();
        return DrawCommand.DRAW.equals(inputCommand);
    }

    private void drawCardForPlayer(BlackjackGame blackjackGame, Name playerName) {
        blackjackGame.drawOneMoreCardForPlayer(playerName);
        showDrawResult(blackjackGame.getPlayerDtoByName(playerName));
    }

    private void doStayForPlayer(BlackjackGame blackjackGame, Name playerName) {
        blackjackGame.doStay(playerName);
        showDrawResult(blackjackGame.getPlayerDtoByName(playerName));
    }

    private void showDrawResult(UserDto userDto) {
        outputView.printPlayerDrawResult(userDto);
    }

    private void progressDealerTurn(BlackjackGame blackjackGame) {
        blackjackGame.drawCardUntilDealerFinished();

        if (blackjackGame.getDealerDrawCount() > 0) {
            outputView.printDealerDrawResult(blackjackGame.getDealerDrawCount());
        }
    }

    private void showUserCardResults(BlackjackGame blackjackGame) {
        UserDto dealerDto = blackjackGame.getDealerDto();
        List<UserDto> allPlayerDtos = blackjackGame.getAllPlayerDtos();

        outputView.printUserCardsWithScore(dealerDto);
        outputView.printAllUserCardsWithScore(allPlayerDtos);
    }

    private void showFinalProfit(BlackjackGame blackjackGame) {
        Map<Name, Integer> playerPrizes = blackjackGame.calculatePlayerResult();

        outputView.printFinalResultHeaderMessage();

        outputView.printDealerPrizeResult(playerPrizes.values().stream()
                .mapToInt(num -> num)
                .sum() * -1);
        outputView.printPlayerPrizeResult(playerPrizes);
    }
}
