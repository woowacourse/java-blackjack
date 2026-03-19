package controller;

import dto.GameInitialInfoDto;
import domain.BettingMoney;
import domain.Name;
import domain.game.BlackJackGame;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameController {

    private final BlackJackGame manager;
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(BlackJackGame manager, InputView inputView, OutputView outputView) {
        this.manager = manager;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        registerPlayer();
        initGame();
        play();
        playDealerTurn();
        outputView.printScoreResults(manager.getScoreResults());
        outputView.printFinalResult(manager.getFinalResult());
    }

    private void registerPlayer() {
        boolean isRegistered = false;
        while (!isRegistered) {
            isRegistered = tryRegisterPlayers();
        }
    }

    private boolean tryRegisterPlayers() {
        try {
            List<String> rawPlayerNames = inputView.readPlayerName();
            List<Name> playerNames = toNames(rawPlayerNames);
            registerPlayersWithBettingMoney(playerNames);
            return true;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return false;
        }
    }

    private List<Name> toNames(List<String> rawPlayerNames) {
        return rawPlayerNames.stream()
                .map(Name::new)
                .toList();
    }

    private void registerPlayersWithBettingMoney(List<Name> playerNames) {
        List<BettingMoney> bettingMoneys = new ArrayList<>();
        for (Name playerName : playerNames) {
            BettingMoney bettingMoney = inputView.readBettingMoney(playerName.getValue());
            bettingMoneys.add(bettingMoney);
        }
        manager.registerPlayers(playerNames, bettingMoneys);
    }

    private void initGame() {
        manager.dealInitialCards();

        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();
        outputView.printInitialInfo(initialInfo);
    }

    private void play() {
        for (Player player : manager.getPlayersToPlay()) {
            playSinglePlayerTurn(player);
        }
    }

    private void playSinglePlayerTurn(Player player) {
        while (player.canDraw()) {
            boolean wantsToDraw = wantsToDraw(player);

            List<String> playerHand = player.getHandToString();
            if (wantsToDraw) {
                playerHand = manager.hit(player);
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
        while (manager.dealerHit()) {
            outputView.printDealerTurn();
        }
    }
}