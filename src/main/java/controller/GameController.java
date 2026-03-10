package controller;

import domain.GameManager;
import domain.Player;
import domain.dto.GameInitialInfoDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class GameController {

    private final GameManager manager;
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(GameManager manager, InputView inputView, OutputView outputView) {
        this.manager = manager;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        registerPlayer();
        // TODO: 베팅 기능 추가 시 플레이어 등록 후 베팅 입력 단계가 추가 필요
        initGame();
        playPlayerTurn();
        playDealerTurn();
        outputView.printScoreResults(manager.getScoreResults());
        outputView.printFinalResult(manager.getFinalResult());
    }

    private void registerPlayer() {
        // TODO: 베팅 기능 추가 시 이름 입력 후 각 플레이어의 베팅 금액도 함께 등록
        List<String> playerNames = inputView.readPlayerName();
        for (String playerName : playerNames) {
            manager.addPlayer(playerName);
        }
    }

    private void initGame() {
        manager.startGame();

        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();
        outputView.printInitialInfo(initialInfo);
    }

    private void playPlayerTurn() {
        for (Player player : manager.getPlayerSequence()) {
            playSinglePlayerTurn(player);
        }
    }

    private void playSinglePlayerTurn(Player player) {
        while (player.canDraw() && wantsToDraw(player)) {
            List<String> playerHand = manager.drawPlayerCard(player);
            outputView.printHand(playerHand, player.getName());
        }
    }

    private boolean wantsToDraw(Player player) {
        return !inputView.readCommand(player.getName()).equals("n");
    }

    private void playDealerTurn() {
        while (manager.proceedDealerTurn()) {
            outputView.printDealerTurn();
        }
    }
}
