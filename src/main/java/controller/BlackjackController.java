package controller;

import constant.PlayerAction;
import domain.bet.Money;
import domain.participant.Names;
import domain.participant.Players;
import dto.BlackjackProfitDto;
import dto.BlackjackResultDto;
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
        collectBets();
        printPlayerCards();
        readHitOrStand();
        boolean dealerHit = blackjackService.drawDealerCard();
        if (dealerHit) {
            outputView.printDealerHit();
        }
        printBlackjackResult();
        printBlackjackStatistics();
    }

    private void collectBets() {
        for (int playerIndex = 0; playerIndex < blackjackService.getPlayerCount(); playerIndex++) {
            String input = inputView.inputBetAmount(blackjackService.getPlayerName(playerIndex));
            blackjackService.receivePlayerBets(playerIndex, Money.from(input));
        }
    }

    private void printPlayerCards() {
        outputView.printPlayers(blackjackService.getAllPlayerNames());
        outputView.printlnPlayer(blackjackService.getDealerPlayerDto());
        outputView.printPlayerList(blackjackService.getAllPlayerDto());
    }

    private void printBlackjackStatistics() {
        BlackjackProfitDto blackjackProfitDto = blackjackService.calculateBlackjackResult();
        outputView.printBlackjackStatistics(blackjackProfitDto);
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
        while (true) {
            String input = inputView.inputHitOrStand(name);
            if (PlayerAction.from(input).isStand()) {
                outputView.printlnPlayer(blackjackService.createPlayerDto(playerIndex));
                break;
            }
            blackjackService.updatePlayer(playerIndex);
            outputView.printlnPlayer(blackjackService.createPlayerDto(playerIndex));
        }
    }
}
