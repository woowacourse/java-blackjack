package controller;

import dto.PlayerResult;
import java.util.ArrayList;
import java.util.List;
import model.Dealer;
import model.Participant;
import model.Player;
import model.Players;
import service.BlackJackService;
import view.OutputView;

public class BlackJackController {
    private static final int INITIAL_DRAW_QUANTITY = 2;

    private final InputController inputController;
    private final BlackJackService blackJackService;

    public BlackJackController(InputController inputController, BlackJackService blackJackService) {
        this.inputController = inputController;
        this.blackJackService = blackJackService;
    }

    public void run() {
        Players players = inputController.getPlayersName();
        Dealer dealer = new Dealer();
        OutputView.printNewLine();

        initDraw(dealer, players);
        drawPlayersTurn(players);
        drawDealer(dealer);
        printPlayersScore(dealer, players);

        OutputView.printResult(blackJackService.getGameResult(players, dealer));
    }

    private void initDraw(Dealer dealer, Players players) {
        initParticipantDraw(dealer);
        List<PlayerResult> playersResult = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            initParticipantDraw(player);
            playersResult.add(toPlayerResult(player));
        }

        OutputView.printInitDeck(playersResult, toPlayerResult(dealer));
    }

    private void initParticipantDraw(Participant participant) {
        for (int i = 0; i < INITIAL_DRAW_QUANTITY; i++) {
            blackJackService.draw(participant);
        }
    }

    private void drawPlayersTurn(Players players) {
        for (Player player : players.getPlayers()) {
            drawPlayerTurns(player);
        }
        OutputView.printNewLine();
    }

    private void drawPlayerTurns(Player player) {
        while (drawPlayerTurn(player)) {
            OutputView.printPlayerCurrentDeck(toPlayerResult(player));
        }
    }

    private boolean drawPlayerTurn(Player player) {
        if (!inputController.getCondition(player.getName())) {
            return false;
        }

        blackJackService.draw(player);

        return !blackJackService.isBust(player);
    }

    private void drawDealer(Dealer dealer) {
        while (blackJackService.isDealerDraw(dealer)) {
            blackJackService.draw(dealer);
            OutputView.printDealerCardDrawMessage();
        }

    }

    private void printPlayersScore(Dealer dealer, Players players) {
        List<PlayerResult> playerResults = new ArrayList<>();
        playerResults.add(toPlayerResult(dealer));

        for (Player player : players.getPlayers()) {
            playerResults.add(toPlayerResult(player));
        }

        OutputView.printPlayersScore(playerResults);
    }

    private PlayerResult toPlayerResult(Participant participant) {
        return new PlayerResult(participant.getNameValue(), participant.getHand(), participant.getScore());
    }

}
