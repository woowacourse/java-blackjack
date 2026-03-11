package controller;

import constant.PlayerAction;
import domain.participant.Names;
import domain.participant.Players;
import dto.BlackjackResultDto;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import java.util.List;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackService = initializeService();
    }

    private BlackjackService initializeService() {
        Names names = Names.from(inputView.inputPlayers());
        Players players = Players.from(names);
        return new BlackjackService(players);
    }

    public void start() {
        blackjackService.dealInitialCards();
        printPlayerCards();
        readHitOrStand();
        boolean dealerHit = blackjackService.drawDealerCard();
        if (dealerHit) {
            outputView.printDealerHit();
        }
        printBlackjackResult();
        printBlackjackStatistics();
    }

    private void printPlayerCards() {
        outputView.printPlayers(blackjackService.getAllPlayerNames());
        outputView.printlnPlayer(blackjackService.getDealerPlayerDto());
        outputView.printPlayerList(blackjackService.getAllPlayerDto());
    }

    private void printBlackjackStatistics() {
        DealerResultDto dealerResultDto = blackjackService.calculateDealerResult();
        List<PlayerResultDto> playerResultDtoList = blackjackService.calculatePlayerResults();
        outputView.printBlackjackStatistics(dealerResultDto, playerResultDtoList);
    }

    private void printBlackjackResult() {
        List<BlackjackResultDto> blackjackResult = blackjackService.generateBlackjackResultDto();
        outputView.printBlackjackResult(blackjackResult);
    }

    private void readHitOrStand() {
        for (int playerIndex = 0; playerIndex < blackjackService.getPlayerCount(); playerIndex++) {
            inputHitOrStand(playerIndex);
        }
    }

    private void inputHitOrStand(int playerIndex) {
        String name = blackjackService.getPlayerName(playerIndex);
        String input = inputView.inputHitOrStand(name);
        PlayerAction playerAction = PlayerAction.from(input);
        if (playerAction.isHit()) {
            outputView.printlnPlayer(blackjackService.createPlayerDto(playerIndex));
            return;
        }

        drawCardOnPlayer(playerIndex);
    }

    private void drawCardOnPlayer(int playerIndex) {
        PlayerAction playerAction = PlayerAction.HIT;
        while (blackjackService.shouldRepeat(playerIndex, playerAction)) {
            blackjackService.updatePlayer(playerIndex);
            outputView.printlnPlayer(blackjackService.createPlayerDto(playerIndex));
            String input = inputView.inputHitOrStand(blackjackService.getPlayerName(playerIndex));
            playerAction = PlayerAction.from(input);
        }
    }
}
