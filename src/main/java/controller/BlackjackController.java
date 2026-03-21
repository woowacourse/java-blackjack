package controller;

import domain.card.CardMachine;
import domain.game.BlackjackGameManager;
import domain.game.BlackjackJudge;
import domain.game.HitOrStand;
import domain.participant.BetAmount;
import domain.participant.Participants;
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

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        List<PlayerName> playerNames = inputPlayerNames();
        List<BetAmount> betAmounts = inputBetAmounts(playerNames);
        BlackjackGameManager blackjackGameManager = new BlackjackGameManager(new CardMachine(), new BlackjackJudge(), Participants.of(playerNames, betAmounts));

        initializeGame(blackjackGameManager);
        inputHitOrStandOnPlayer(blackjackGameManager);
        while (blackjackGameManager.drawDealerCard()) {
            outputView.printDealerHit();
        }

        printBlackjackResult(blackjackGameManager);
        printBlackjackStatistics(blackjackGameManager);
    }

    private void initializeGame(BlackjackGameManager blackjackGameManager) {
        blackjackGameManager.drawInitialCards();

        List<ParticipantDto> playerDtoList = blackjackGameManager.generatePlayerDtoList();
        outputView.printPlayers(playerDtoList);

        ParticipantDto dealerDto = blackjackGameManager.generateInitialDealerDto();
        outputView.printHandList(dealerDto, playerDtoList);
    }

    private List<PlayerName> inputPlayerNames() {
        List<String> names = Parser.parseInput(inputView.inputPlayers());
        List<PlayerName> playerNames = new ArrayList<>();
        for (String name : names) {
            playerNames.add(new PlayerName(name));
        }
        return playerNames;
    }

    private List<BetAmount> inputBetAmounts(List<PlayerName> playerNames) {
        List<BetAmount> betAmounts = new ArrayList<>();
        for (PlayerName playerName : playerNames) {
            String betAmountInput = inputView.inputBetAmount(playerName.name());
            betAmounts.add(new BetAmount(betAmountInput));
        }
        return betAmounts;
    }

    private void inputHitOrStandOnPlayer(BlackjackGameManager blackjackGameManager) {
        List<ParticipantDto> playersDtoList = blackjackGameManager.generatePlayerDtoList();
        for (ParticipantDto playerDto : playersDtoList) {
            inputHitOrStand(playerDto.name(), playerDto.hand(), blackjackGameManager);
        }
    }

    private void inputHitOrStand(String name, List<String> hand, BlackjackGameManager blackjackGameManager) {
        HitOrStand hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        if (hitOrStand.isStand()) {
            outputView.printlnHand(name, hand);
            return;
        }

        drawCardOnPlayer(name, blackjackGameManager);
    }

    private void drawCardOnPlayer(String name, BlackjackGameManager blackjackGameManager) {
        do {
            ParticipantDto playerDto = blackjackGameManager.updatePlayer(name);
            outputView.printlnHand(name, playerDto.hand());
        } while (canDrawContinue(name, blackjackGameManager));
    }

    private boolean canDrawContinue(String name, BlackjackGameManager blackjackGameManager) {
        return !blackjackGameManager.isPlayerBust(name) && HitOrStand.from(inputView.inputHitOrStand(name)).isHit();
    }

    private void printBlackjackResult(BlackjackGameManager blackjackGameManager) {
        BlackjackResultDto blackjackResultDto = blackjackGameManager.getBlackjackResult();
        outputView.printBlackjackResult(blackjackResultDto);
    }

    private void printBlackjackStatistics(BlackjackGameManager blackjackGameManager) {
        BlackjackStatisticsDto blackjackStatistics = blackjackGameManager.getBlackjackStatistics();
        outputView.printBlackjackStatistics(blackjackStatistics);
    }
}
