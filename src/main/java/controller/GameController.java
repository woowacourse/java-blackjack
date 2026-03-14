package controller;

import domain.Players;
import domain.betting.Money;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.PlayerHandDto;
import service.BattingCalculateService;
import service.GameService;
import util.HitOption;
import util.InputBattingParser;
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
        Players players = inputPlayers();
        Dealer dealer = new Dealer();
        GameService gameService = new GameService(players, dealer);
        BattingCalculateService bettingCalculateService = new BattingCalculateService(players, dealer);

        playerBatting(players);
        outputView.printStartGame(gameService.startGame());

        boolean dealerIsBlackJack = processDealerBlackJack(gameService);
        if (!dealerIsBlackJack) {
            processGame(gameService);
        }

        outputView.printScore(gameService.getTotalScore());
        outputView.printBattingResults(bettingCalculateService.getBattingResult());
    }

    private Players inputPlayers() {
        while (true) {
            try {
                String rawPlayerNames = inputView.readPlayerNames();
                return Players.fromString(rawPlayerNames);
            } catch (IllegalArgumentException exception) {
                outputView.printInputErrorMessage(exception.getMessage());
            }
        }
    }

    private HitOption inputHitOption(Player player) {
        while (true) {
            try {
                String rawHitOption = inputView.readHitOption(player.getName());
                return HitOption.of(rawHitOption);
            } catch (IllegalArgumentException exception) {
                outputView.printInputErrorMessage(exception.getMessage());
            }
        }
    }

    private Money inputBattingMoney(Player player) {
        while (true) {
            try {
                String rawBattingMoney = inputView.readBatting(player.getName());
                return InputBattingParser.parseBattingParser(rawBattingMoney);
            } catch (IllegalArgumentException exception) {
                outputView.printInputErrorMessage(exception.getMessage());
            }
        }
    }

    private void processGame(GameService gameService) {
        for (Player player : gameService.getPlayers()) {
            playerTurn(player, gameService);
        }
        dealerTurn(gameService);
    }

    private boolean processDealerBlackJack(GameService gameService) {
        Dealer dealer = gameService.getDealer();
        if (dealer.isBlackJack()) {
            endGameImmediately(gameService.getPlayers());
            outputView.printDealerBlackJack();
            return true;
        }
        return false;
    }

    private void endGameImmediately(Players players) {
        for (Player player : players) {
            forceStay(player);
        }
    }

    private void forceStay(Player player) {
        if (player.isRunning()) {
            player.stay();
        }
    }

    private void playerTurn(Player player, GameService gameService) {
        while (player.isRunning() && inputHitOption(player) == HitOption.YES) {
            gameService.hit(player);
            outputView.printHandCard(PlayerHandDto.from(player));
        }

        if (player.isRunning()) {
            gameService.stay(player);
            outputView.printHandCard(PlayerHandDto.from(player));
        }
    }

    private void dealerTurn(GameService gameService) {
        Dealer dealer = gameService.getDealer();

        while (dealer.isRunning() && dealer.isReceiveCard()) {
            gameService.hit(dealer);
            outputView.printDealerReceiveCard();
        }

        if (dealer.isRunning()) {
            gameService.stay(dealer);
        }
    }

    private void playerBatting(Players players) {
        for (Player player : players) {
            player.bettingMoney(inputBattingMoney(player));
        }
    }
}
