package view;

import domain.betting.BetAmount;
import domain.participant.ParticipantInitialInformation;
import domain.participant.ParticipantName;
import domain.participant.dto.ParticipantHandDto;
import domain.result.dto.BettingProfitDto;
import domain.result.dto.ParticipantGameResultDto;
import java.util.List;
import java.util.function.Supplier;

public class ApplicationView {

    private static final String DELIMITER = ",";
    private static final String INTENTION_YES = "y";
    private static final String INTENTION_NO = "n";

    private final InputReader reader;
    private final OutputWriter writer;

    private ApplicationView(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static ApplicationView of(InputReader reader, OutputWriter writer) {
        return new ApplicationView(reader, writer);
    }

    public List<ParticipantInitialInformation> requestInitialInformation() {
        List<ParticipantName> participantNames = requestParticipantNames();

        return participantNames.stream()
                .map(name -> ParticipantInitialInformation.of(name, requestBetAmount(name.name())))
                .toList();
    }

    public boolean requestDrawCardDecision(String playerName) {
        return retry(() -> readDrawCardDecision(playerName));
    }

    public void printInitialDeal(List<String> playerNames, int initialCardCount) {
        String formattedNames = String.join(DELIMITER, playerNames);
        writer.printDealInitialCardMessage(formattedNames, initialCardCount);
    }

    public void printPlayerHands(List<ParticipantHandDto> playerHands) {
        for (ParticipantHandDto playerHand : playerHands) {
            printParticipantHand(playerHand);
        }
    }

    public void printParticipantHand(ParticipantHandDto playerHand) {
        writer.printAllParticipantsHand(
                playerHand.playerName(),
                formatHand(playerHand.handOnCards())
        );
    }

    public void printDealerDrawCard() {
        writer.printDealerAdditionalDrawCardMessage();
    }

    public void printBettingResults(
            BettingProfitDto dealerBettingResultDto,
            List<BettingProfitDto> playerBettingResultDtos
    ) {
        writer.printFinalBettingResultTitleMessage();
        printBettingResult(dealerBettingResultDto);
        playerBettingResultDtos.forEach(this::printBettingResult);
    }

    private void printBettingResult(BettingProfitDto dto) {
        writer.printBettingResult(dto.playerName(), dto.totalProfit());
    }

    public void printParticipantResult(ParticipantGameResultDto playerResult) {
        ParticipantHandDto playerHand = playerResult.playerHand();
        writer.printFinalResultMessage(
                playerHand.playerName(),
                formatHand(playerHand.handOnCards()),
                playerResult.resultScore()
        );
    }

    private List<ParticipantName> requestParticipantNames() {
        return retry(this::readParticipantNames);
    }

    private List<ParticipantName> readParticipantNames() {
        writer.printInputNameGuideMessage();
        List<String> names = reader.readInputBasedOnSeparator(DELIMITER);
        return names.stream()
                .map(ParticipantName::from)
                .toList();
    }


    private BetAmount requestBetAmount(String participantName) {
        return retry(() -> readBetAmount(participantName));
    }

    private BetAmount readBetAmount(String participantName) {
        writer.printInputBetAmountGuideMessage(participantName);
        int betAmount = reader.readInteger();
        return BetAmount.from(betAmount);
    }

    private boolean readDrawCardDecision(String playerName) {
        writer.printDrawCardGuideMessage(playerName);
        String input = reader.readInputOnlyCandidate(List.of(INTENTION_YES, INTENTION_NO));
        return input.equals(INTENTION_YES);
    }

    private String formatHand(List<String> hands) {
        return String.join(", ", hands);
    }

    private <T> T retry(Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            } catch (IllegalArgumentException e) {
                writer.printErrorMessage(e.getMessage());
            }
        }
    }

}
