package controller;

import domain.card.CardMachine;
import domain.game.BlackjackGame;
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

public class BlackjackGameRunner {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        List<PlayerName> playerNames = inputPlayerNames();
        List<BetAmount> betAmounts = inputBetAmounts(playerNames);
        BlackjackGame blackjackGame = new BlackjackGame(new CardMachine(), new BlackjackJudge(), Participants.of(playerNames, betAmounts));

        initializeGame(blackjackGame);
        inputHitOrStandOnPlayer(blackjackGame);
        while (blackjackGame.drawDealerCard()) {
            outputView.printDealerHit();
        }

        printBlackjackResult(blackjackGame);
        printBlackjackStatistics(blackjackGame);
    }

    private void initializeGame(BlackjackGame blackjackGame) {
        blackjackGame.drawInitialCards();

        List<ParticipantDto> playerDtoList = blackjackGame.generatePlayerDtoList();
        outputView.printPlayers(playerDtoList);

        ParticipantDto dealerDto = blackjackGame.generateInitialDealerDto();
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

    private void inputHitOrStandOnPlayer(BlackjackGame blackjackGame) {
        List<ParticipantDto> playersDtoList = blackjackGame.generatePlayerDtoList();
        for (ParticipantDto playerDto : playersDtoList) {
            inputHitOrStand(playerDto.name(), playerDto.hand(), blackjackGame);
        }
    }

    private void inputHitOrStand(String name, List<String> hand, BlackjackGame blackjackGame) {
        HitOrStand hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        if (hitOrStand.isStand()) {
            outputView.printlnHand(name, hand);
            return;
        }

        drawCardOnPlayer(name, blackjackGame);
    }

    private void drawCardOnPlayer(String name, BlackjackGame blackjackGame) {
        do {
            ParticipantDto playerDto = blackjackGame.updatePlayer(name);
            outputView.printlnHand(name, playerDto.hand());
        } while (canDrawContinue(name, blackjackGame));
    }

    private boolean canDrawContinue(String name, BlackjackGame blackjackGame) {
        return !blackjackGame.isPlayerBust(name) && HitOrStand.from(inputView.inputHitOrStand(name)).isHit();
    }

    private void printBlackjackResult(BlackjackGame blackjackGame) {
        BlackjackResultDto blackjackResultDto = blackjackGame.getBlackjackResult();
        outputView.printBlackjackResult(blackjackResultDto);
    }

    private void printBlackjackStatistics(BlackjackGame blackjackGame) {
        BlackjackStatisticsDto blackjackStatistics = blackjackGame.getBlackjackStatistics();
        outputView.printBlackjackStatistics(blackjackStatistics);
    }
}
