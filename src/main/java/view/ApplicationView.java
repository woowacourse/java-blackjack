package view;

import domain.betiing.BetAmount;
import domain.participant.ParticipantInitialInformation;
import domain.result.dto.BettingProfitDto;
import domain.result.dto.GameResultDto;
import domain.participant.ParticipantName;
import domain.participant.dto.ParticipantHandDto;
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

    public List<ParticipantInitialInformation> requestInitialInformations() {
        List<ParticipantName> participantNames = requestPlayerNames();
        return participantNames.stream().map(name -> {
            BetAmount betAmount = requestBetAmount(name.name());
            return ParticipantInitialInformation.of(name, betAmount);
        }).toList();
    }

    private List<ParticipantName> requestPlayerNames() {
        return retry(this::readPlayerNames);
    }

    private BetAmount requestBetAmount(String name) {
        return retry(() -> readBetAmounts(name));
    }

    private List<ParticipantName> readPlayerNames() {
        writer.printInputNameGuideMessage();
        List<String> names = reader.readInputBasedOnSeparator(DELIMITER);

        return names.stream().map(ParticipantName::from).toList();
    }

    private BetAmount readBetAmounts(String participantName) {
        writer.printInputBetAmountGuideMessage(participantName);
        int betAmount = reader.readInteger();
        return BetAmount.from(betAmount);
    }
    public boolean requestDrawCardIntention(String playerName) {
        return retry(() -> readDrawCardIntention(playerName));
    }

    private boolean readDrawCardIntention(String playerName) {
        writer.printDrawCardGuideMessage(playerName);
        String input = reader.readInputOnlyCandidate(List.of(INTENTION_YES, INTENTION_NO));

        return input.equals(INTENTION_YES);
    }

    public void printInitialHandOutResult(List<String> playerNames, int initialCardCount) {
        String formattedNames = String.join(DELIMITER, playerNames);
        writer.printDealInitialCardMessage(formattedNames, initialCardCount);
    }

    public void printAllPlayersHand(List<ParticipantHandDto> playerHands) {
        for (ParticipantHandDto playerHand : playerHands) {
            printParticipantHand(playerHand);
        }
    }

    public void printParticipantHand(ParticipantHandDto playerHand) {
        writer.printAllParticipantsHand(playerHand.playerName(), formatHands(playerHand.handOnCards()));
    }

    public void printDealerAdditionalDrawCardMessage() {
        writer.printDealerAdditionalDrawCardMessage();
    }

    public void printFinalBettingResult(List<BettingProfitDto> bettingProfitDtos) {
        writer.printFinalBettingResultTitleMessage();
        bettingProfitDtos.forEach(bettingProfitDto ->
                writer.printBettingResult(bettingProfitDto.playerName(), bettingProfitDto.totalProfit()));
    }

    public void printFinalResultMessage(GameResultDto resultAnalysis) {
        writer.printFinalResultTitleMessage();
        printAllPlayersResult(resultAnalysis);
    }

    private void printAllPlayersResult(GameResultDto resultAnalysis) {
        writer.printFinalResultOfDealer(resultAnalysis.dealerGameResultDto().resultStatistic());
        resultAnalysis.playerGameResultDtos().forEach(player ->
                writer.printFinalResultOfPlayer(player.playerName(), player.gameResult().displayName())
        );
    }

    public void printFinalResultMessage(ParticipantGameResultDto playerResult) {
        ParticipantHandDto playerHand = playerResult.playerHand();
        writer.printFinalResultMessage(playerHand.playerName(), formatHands(playerHand.handOnCards()), playerResult.resultScore());
    }

    private String formatHands(List<String> hands) {
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
