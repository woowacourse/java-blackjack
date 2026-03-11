package controller;

import domain.Command;
import domain.Deck;
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

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = getPlayers();
        Dealer dealer = new Dealer();
        GameManager gameManager = getManager();

        initializeGame(gameManager, dealer, players);
        playGame(players, gameManager, dealer);
        resultPhase(players, dealer);
    }

    private void resultPhase(Players players, Dealer dealer) {
        Referee referee = new Referee();
        Map<String, Integer> scoreByPlayerNames = new LinkedHashMap<>();
        for (Player player : players) {
            scoreByPlayerNames.put(player.getName(), player.getScore());
        }
        GameResultDto gameResultDto = referee.createGameResult(dealer.getScore(), scoreByPlayerNames);
        outputView.printResult(gameResultDto);
    }

    private void playGame(Players players, GameManager gameManager, Dealer dealer) {
        playPlayersTurn(players, gameManager);
        playDealerTurn(players, gameManager, dealer);
    }

    private void playDealerTurn(Players players, GameManager gameManager, Dealer dealer) {
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

    private void playPlayersTurn(Players players, GameManager gameManager) {
        for (Player player : players) {
            playPlayerTurn(gameManager, player);
        }
        outputView.printNewLine();
    }

    private void playPlayerTurn(GameManager gameManager, Player player) {
        while (shouldDrawCard(player)) {
            drawAndPrint(gameManager, player);
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

    private void drawAndPrint(GameManager gameManager, Player player) {
        gameManager.dealCard(player);
        outputView.printParticipantCard(player.getName(),
                gameManager.getCardsResult(player).getFormattedCards());
    }

    private void initializeGame(GameManager gameManager, Dealer dealer, Players players) {
        gameManager.dealCard(dealer);
        gameManager.dealCard(dealer);
        gameManager.dealCardTo(players, 2);

        Map<String, CardsSnapshot> result = new LinkedHashMap<>();
        result.put(dealer.getName(), gameManager.getStartingCard(dealer));
        for (Player player : players) {
            result.put(player.getName(), gameManager.getCardsResult(player));
        }

        outputView.printGameInitResult(result);
        outputView.printNewLine();
    }

    private GameManager getManager() {
        Deck deck = Deck.create();
        deck.shuffle();
        return new GameManager(deck);
    }

    private Players getPlayers() {
        String rawPlayerName = inputView.readPlayerName();
        return PlayerParser.parseToPlayers(rawPlayerName);
    }
}
