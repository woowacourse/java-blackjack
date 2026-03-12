package controller;

import domain.Command;
import domain.GameManager;
import domain.GameResultDto;
import domain.Referee;
import domain.card.CardsSnapshot;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerParser;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final GameManager gameManager;

    public GameController(InputView inputView, OutputView outputView, GameManager manager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameManager = manager;
    }

    public void run() {
        Players players = readPlayers();
        Dealer dealer = new Dealer();

        readyPhase(dealer, players);
        playPhase(dealer, players);
        resultPhase(dealer, players);
    }

    private void resultPhase(Dealer dealer, Players players) {
        Referee referee = new Referee();
        Map<String, Integer> scoreByPlayerNames = new LinkedHashMap<>();
        for (Player player : players) {
            scoreByPlayerNames.put(player.getName(), player.getScore());
        }
        GameResultDto gameResultDto = referee.createGameResult(dealer.getScore(), scoreByPlayerNames);
        outputView.printResult(gameResultDto);
    }

    private void playPhase(Dealer dealer, Players players) {
        playPlayersTurn(players);
        playDealerTurn(players, dealer);
    }

    private void playDealerTurn(Players players, Dealer dealer) {
        while (dealer.canReceive()) {
            gameManager.dealCard(dealer);
            outputView.printCompleteDealerTurn();
        }
        outputView.printNewLine();

        outputView.printParticipantResult(dealer.getName(), gameManager.getCardsResult(dealer).getFormattedCards(),
                dealer.getScore());
        for (Player player : players) {
            outputView.printParticipantResult(player.getName(), gameManager.getCardsResult(player).getFormattedCards(),
                    player.getScore());
        }
        outputView.printNewLine();
    }

    private void playPlayersTurn(Players players) {
        for (Player player : players) {
            playPlayerTurn(player);
        }
        outputView.printNewLine();
    }

    private void playPlayerTurn(Player player) {
        while (shouldDrawCard(player)) {
            drawAndPrint(player);
        }
    }

    private boolean shouldDrawCard(Player player) {
        if (!player.canReceive()) {
            return false;
        }
        return isUserWantHit(player);
    }

    private boolean isUserWantHit(Player player) {
        Command command = Command.from(inputView.askPlayHit(player.getName()));
        return !command.isNo();
    }

    private void drawAndPrint(Player player) {
        gameManager.dealCard(player);
        outputView.printParticipantCard(player.getName(),
                gameManager.getCardsResult(player).getFormattedCards());
    }

    private void readyPhase(Dealer dealer, Players players) {
        gameManager.dealStartingCards(dealer);
        gameManager.dealCardTo(players, 2);

        Map<String, CardsSnapshot> result = new LinkedHashMap<>();
        result.put(dealer.getName(), gameManager.getStartingCard(dealer));
        for (Player player : players) {
            result.put(player.getName(), gameManager.getCardsResult(player));
        }

        outputView.printGameInitResult(result);
        outputView.printNewLine();
    }

    private Players readPlayers() {
        String rawPlayerName = inputView.readPlayerName();
        return PlayerParser.parseToPlayers(rawPlayerName);
    }
}
