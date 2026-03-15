package controller;

import dto.GameInitialInfoDto;
import domain.game.GameManager;
import domain.participant.Player;
import java.util.ArrayList;
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
        initGame();
        playPlayerTurn();
        playDealerTurn();
        outputView.printScoreResults(manager.getScoreResults());
        outputView.printFinalResult(manager.getFinalResult());
    }

    private void registerPlayer() {
        while (true) {
            try {
                List<String> playerNames = inputView.readPlayerName();
                validatePlayerNames(playerNames);
                registerPlayersWithBettingMoney(playerNames);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void registerPlayersWithBettingMoney(List<String> playerNames) {
        List<Integer> bettingMoneyList = new ArrayList<>();
        for (String playerName : playerNames) {
            int bettingMoney = inputView.readBettingMoney(playerName);
            bettingMoneyList.add(bettingMoney);
        }
        manager.registerPlayers(playerNames, bettingMoneyList);
    }

    private void initGame() {
        manager.startGame();

        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();
        outputView.printInitialInfo(initialInfo);
    }

    private void playPlayerTurn() {
        for (Player player : manager.getPlayersToPlay()) {
            playSinglePlayerTurn(player);
        }
    }

    private void playSinglePlayerTurn(Player player) {
        while (player.canDraw()) {
            boolean wantsToDraw = wantsToDraw(player);

            List<String> playerHand = player.getHandToString();
            if(wantsToDraw){
                playerHand = manager.drawPlayerCard(player);
            }
            outputView.printHand(playerHand, player.getName());

            if(!wantsToDraw) {
                break;
            }
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

    private void validatePlayerNames(List<String> playerNames) {
        if (playerNames == null || playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름을 입력해야 합니다.");
        }

        if (playerNames.stream().anyMatch(name -> name == null || name.isBlank())) {
            throw new IllegalArgumentException("플레이어 이름은 비어 있을 수 없습니다.");
        }
    }
}