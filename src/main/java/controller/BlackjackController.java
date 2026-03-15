package controller;

import domain.game.BlackjackGameManager;
import domain.game.HitOrStand;
import domain.participant.BetAmount;
import domain.participant.PlayerName;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;
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
        List<PlayerName> playerNames = inputPlayerNames();
        List<BetAmount> betAmounts = inputBetAmounts(playerNames);
        blackjackGameManager.createParticipants(playerNames, betAmounts);

        blackjackGameManager.drawInitialCards();

        List<ParticipantDto> playerDtoList = blackjackGameManager.generatePlayerDtoList();
        outputView.printPlayers(playerDtoList);

        ParticipantDto dealerDto = blackjackGameManager.generateInitialDealerDto();
        outputView.printHandList(dealerDto, playerDtoList);
    }

    private List<BetAmount> inputBetAmounts(List<PlayerName> playerNames) {
        List<BetAmount> betAmounts = new ArrayList<>();
        for (PlayerName playerName : playerNames) {
            String betAmountInput = inputView.inputBetAmount(playerName.name());
            betAmounts.add(new BetAmount(betAmountInput));
        }
        return betAmounts;
    }

    private List<PlayerName> inputPlayerNames() {
        List<String> names = Parser.parseInput(inputView.inputPlayers());
        List<PlayerName> playerNames = new ArrayList<>();
        for (String name : names) {
            playerNames.add(new PlayerName(name));
        }
        return playerNames;
    }

    private void inputHitOrStandOnPlayer() {
        List<ParticipantDto> playersDtoList = blackjackGameManager.generatePlayerDtoList();
        for (ParticipantDto playerDto : playersDtoList) {
            inputHitOrStand(playerDto.name(), playerDto.hand());
        }
    }

    private void inputHitOrStand(String name, List<String> hand) {
        HitOrStand hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        if (hitOrStand.isStand()) {
            outputView.printlnHand(name, hand);
            return;
        }

        drawCardOnPlayer(name, hitOrStand);
    }

    private void drawCardOnPlayer(String name, HitOrStand hitOrStand) {
        while (canDrawContinue(name, hitOrStand)) {
            ParticipantDto playerDto = blackjackGameManager.updatePlayer(name);
            outputView.printlnHand(name, playerDto.hand());
            hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        }
    }

    private boolean canDrawContinue(String name, HitOrStand hitOrStand) {
        return hitOrStand.isHit() && !blackjackGameManager.playerIsBust(name);
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
