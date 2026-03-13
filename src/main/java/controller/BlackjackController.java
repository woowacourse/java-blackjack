package controller;

import domain.game.HitOrStand;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import java.util.List;
import domain.game.BlackjackGameManager;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGameManager blackjackGameManager;

    public BlackjackController(InputView inputView, OutputView outputView, BlackjackGameManager blackjackGameManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGameManager = blackjackGameManager;
    }

    public void start() {
        initializeGame();
        inputHitOrStandOnPlayer();
        while (blackjackGameManager.drawDealerCard()) {
            outputView.printDealerHit();
        }

        printBlackjackResult();
        printBlackjackStatistics();
    }

    private void initializeGame() {
        inputPlayers();
        blackjackGameManager.drawInitialCards();

        List<ParticipantDto> playerDtoList = blackjackGameManager.generatePlayerDtoList();
        outputView.printPlayers(playerDtoList);

        ParticipantDto dealerDto = blackjackGameManager.generateInitialDealerDto();
        outputView.printHandList(dealerDto, playerDtoList);
    }

    private void inputPlayers() {
        List<String> input = Parser.parseInput(inputView.inputPlayers());
        blackjackGameManager.createParticipants(input);
    }

    private void inputHitOrStandOnPlayer() {
        List<ParticipantDto> playersDtoList = blackjackGameManager.generatePlayerDtoList();
        for (ParticipantDto playerDto : playersDtoList) {
            inputHitOrStand(playerDto.name(), playerDto.hand());
        }
    }

    private void inputHitOrStand(String name, List<String> hand) {
        HitOrStand hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        if (blackjackGameManager.isStand(hitOrStand)) {
            outputView.printlnHand(name, hand);
            return;
        }

        drawCardOnPlayer(name, hitOrStand);
    }

    private void drawCardOnPlayer(String name, HitOrStand hitOrStand) {
        while (blackjackGameManager.isHit(hitOrStand)) {
            ParticipantDto playerDto = blackjackGameManager.updatePlayer(name);
            outputView.printlnHand(name, playerDto.hand());
            hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        }
    }

    private void printBlackjackResult() {
        BlackjackResultDto blackjackResultDto = blackjackGameManager.getBlackjackResult();
        outputView.printBlackjackResult(blackjackResultDto);
    }

    private void printBlackjackStatistics() {
        BlackjackStatisticsDto blackjackStatistics = blackjackGameManager.getBlackjackStatistics();
        outputView.printBlackjackStatistics(blackjackStatistics);
    }
}
