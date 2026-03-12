package controller;

import constant.HitOrStand;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import java.util.List;
import service.BlackjackService;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(InputView inputView, OutputView outputView, BlackjackService blackjackService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackService = blackjackService;
    }

    public void start() {
        initializeGame();
        inputHitOrStandOnPlayer();
        while (blackjackService.drawDealerCard()) {
            outputView.printDealerHit();
        }

        printBlackjackResult();
        printBlackjackStatistics();
    }

    private void initializeGame() {
        inputPlayers();
        blackjackService.drawInitialCards();

        List<ParticipantDto> playerDtoList = blackjackService.generatePlayerDtoList();
        outputView.printPlayers(playerDtoList);

        ParticipantDto dealerDto = blackjackService.generateInitialDealerDto();
        outputView.printHandList(dealerDto, playerDtoList);
    }

    private void inputPlayers() {
        List<String> input = Parser.parseInput(inputView.inputPlayers());
        blackjackService.createPlayers(input);
    }

    private void inputHitOrStandOnPlayer() {
        List<ParticipantDto> playersDtoList = blackjackService.generatePlayerDtoList();
        for (ParticipantDto playerDto : playersDtoList) {
            inputHitOrStand(playerDto.name(), playerDto.hand());
        }
    }

    private void inputHitOrStand(String name, List<String> hand) {
        HitOrStand hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        if (blackjackService.isStand(hitOrStand)) {
            outputView.printlnHand(name, hand);
            return;
        }

        drawCardOnPlayer(name, hitOrStand);
    }

    private void drawCardOnPlayer(String name, HitOrStand hitOrStand) {
        while (blackjackService.isHit(hitOrStand)) {
            ParticipantDto playerDto = blackjackService.updatePlayer(name);
            outputView.printlnHand(name, playerDto.hand());
            hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        }
    }

    private void printBlackjackResult() {
        BlackjackResultDto blackjackResultDto = blackjackService.getBlackjackResult();
        outputView.printBlackjackResult(blackjackResultDto);
    }

    private void printBlackjackStatistics() {
        BlackjackStatisticsDto blackjackStatistics = blackjackService.getBlackjackStatistics();
        outputView.printBlackjackStatistics(blackjackStatistics);
    }
}
