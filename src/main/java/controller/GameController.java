package controller;

import dto.GameInitialInfoDto;
import domain.BettingMoney;
import domain.Name;
import domain.game.GameManager;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

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
                List<String> rawPlayerNames = inputView.readPlayerName();
                List<Name> playerNames = toNames(rawPlayerNames);
                registerPlayersWithBettingMoney(playerNames);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private List<Name> toNames(List<String> rawPlayerNames) {
        return rawPlayerNames.stream()
                .map(Name::new)
                .toList();
    }

    private void registerPlayersWithBettingMoney(List<Name> playerNames) {
        List<BettingMoney> bettingMoneyList = new ArrayList<>();
        for (Name playerName : playerNames) {
            BettingMoney bettingMoney = inputView.readBettingMoney(playerName.getValue());
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
            if (wantsToDraw) {
                playerHand = manager.drawPlayerCard(player);
            }
            outputView.printHand(playerHand, player.getName());

            if (!wantsToDraw) {
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
}