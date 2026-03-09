package controller;

import dto.DealerInfoDto;
import dto.PlayerInfoDto;
import service.GameService;
import view.InputView;
import view.ResultView;

import java.util.List;

public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        registerPlayers();
        initAllParticipantsCard();
        printInitialCardResult();

        playerGroupHit();
        dealerHit();

        finalizeGameResult();
        printFinalCardResult();
        printRankResult();
    }

    private void registerPlayers() {
        List<String> names = InputView.askName();
        gameService.registerPlayers(names);
    }

    private void initAllParticipantsCard() {
        gameService.initAllParticipantsCard();
    }

    private void printInitialCardResult() {
        DealerInfoDto dealerInfoDto = gameService.getDealerInfo();
        List<PlayerInfoDto> playerInfoDtos = gameService.getAllPlayersInfo();
        ResultView.printStartPlayersCards(dealerInfoDto, playerInfoDtos);
    }

    private void playerGroupHit() {
        while (gameService.isRemainPlayer()) {
            playerHit();
            gameService.nextPlayer();
        }
    }

    private void playerHit() {
        while(canHitAndDraw()){
            ResultView.printPlayerCards(gameService.getCurrentPlayerName(), gameService.getCurrentPlayerCards());
        }
    }

    private boolean canHitAndDraw(){
        if (gameService.isCurrentPlayerBust()) {
            return false;
        }

        if (InputView.askHit(gameService.getCurrentPlayerName())){
            gameService.currentPlayerHit();
            return true;
        }

        ResultView.printPlayerCards(gameService.getCurrentPlayerName(), gameService.getCurrentPlayerCards());
        return false;
    }

    private void finalizeGameResult() {
        gameService.finalizeGameResult();
    }

    private void printFinalCardResult() {
        DealerInfoDto dealerInfoDto = gameService.getDealerInfo();
        List<PlayerInfoDto> playerInfoDtos = gameService.getAllPlayersInfo();

        ResultView.printCardsAndScoreResult(dealerInfoDto, playerInfoDtos);
    }

    private void dealerHit() {
        if (gameService.isDealerHit()) {
            ResultView.printDealerOneMoreCard();
        }
    }

    private void printRankResult() {
        DealerInfoDto dealerInfoDto = gameService.getDealerInfo();
        List<PlayerInfoDto> playerInfoDtos = gameService.getAllPlayersInfo();

        ResultView.printRankResult(dealerInfoDto, playerInfoDtos);
    }
}
