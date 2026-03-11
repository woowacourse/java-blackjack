package controller;

import constant.HitOrStand;
import constant.PolicyConstant;
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
        List<ParticipantDto> participantDtoList = initializeGame();
        inputHitOrStandOnPlayer(participantDtoList);
        while (blackjackService.drawDealerCard()) {
            outputView.printDealerHit();
        }

        printBlackjackResult();
        printBlackjackStatistics();
    }

    private List<ParticipantDto> initializeGame() {
        inputPlayers();
        blackjackService.dealInitialCards();
        List<ParticipantDto> participantDtoList = blackjackService.generateInitialParticipantDtoList();
        outputView.printPlayers(participantDtoList);
        outputView.printHandList(participantDtoList);
        return participantDtoList;
    }

    private void inputPlayers() {
        List<String> input = Parser.parseInput(inputView.inputPlayers(), PolicyConstant.DELIMITER);
        blackjackService.createPlayers(input);
    }

    private void inputHitOrStandOnPlayer(List<ParticipantDto> participantDtoList) {
        for (ParticipantDto participantDto : participantDtoList) {
            if (participantDto.name().equals(PolicyConstant.DEALER_NAME)) continue;
            inputHitOrStand(participantDto.name(), participantDto.hand());
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
        List<ParticipantDto> blackjackResult = blackjackService.getBlackjackResult();
        outputView.printBlackjackResult(blackjackResult);
    }

    private void printBlackjackStatistics() {
        BlackjackStatisticsDto blackjackStatistics = blackjackService.getBlackjackStatistics();
        outputView.printBlackjackStatistics(blackjackStatistics);
    }
}
