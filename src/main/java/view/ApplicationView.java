package view;

import domain.result.dto.GameResultAnalysisDto;
import domain.intention.DrawCardIntetion;
import domain.participant.ParticipantName;
import domain.participant.dto.PlayerHandDto;
import domain.participant.dto.PlayerResultDto;

import java.util.List;
import java.util.function.Supplier;

public class ApplicationView {

    private static final String DELIMITER = ",";

    private final InputReader reader;
    private final OutputWriter writer;

    private ApplicationView(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static ApplicationView of(InputReader reader, OutputWriter writer) {
        return new ApplicationView(reader, writer);
    }

    public List<ParticipantName> requestPlayerNames() {
        return retry(this::readPlayerNames);
    }

    private List<ParticipantName> readPlayerNames() {
        writer.printInputNameGuideMessage();
        List<String> names = reader.readInputBasedOnSeparator(DELIMITER);

        return names.stream().map(ParticipantName::from).toList();
    }

    public DrawCardIntetion requestDrawCardIntention(String playerName) {
        return retry(() -> readDrawCardIntention(playerName));
    }

    private DrawCardIntetion readDrawCardIntention(String playerName) {
        writer.printDrawCardGuideMessage(playerName);
        String input = reader.readInput();
        return DrawCardIntetion.from(input);
    }

    public void printInitialHandOutResult(List<String> playerNames, int initialCardCount) {
        String formattedNames = String.join(DELIMITER, playerNames);
        writer.printDealInitialCardMessage(formattedNames, initialCardCount);
    }

    public void printAllPlayersHand(List<PlayerHandDto> playerHands) {
        for (PlayerHandDto playerHand : playerHands) {
            printParticipantHand(playerHand);
        }
    }

    public void printParticipantHand(PlayerHandDto playerHand) {
        writer.printAllParticipantsHand(playerHand.playerName(), playerHand.handOnCards());
    }

    public void printDealerAdditionalDrawCardMessage() {
        writer.printDealerAdditionalDrawCardMessage();
    }

    public void printFinalResultMessage(GameResultAnalysisDto resultAnalysis) {
        writer.printFinalResultTitleMessage();
        printAllPlayersResult(resultAnalysis);
    }

    private void printAllPlayersResult(GameResultAnalysisDto resultAnalysis) {
        writer.printFinalResultOfDealer(resultAnalysis.getDealerResult());
        resultAnalysis.playerGameResults().forEach(player ->
                writer.printFinalResultOfPlayer(player.playerName(), player.gameResult().displayName())
        );
    }

    public void printFinalResultMessage(PlayerResultDto playerResult) {
        PlayerHandDto playerHand = playerResult.playerHand();
        writer.printFinalResultMessage(playerHand.playerName(), playerHand.handOnCards(), playerResult.resultScore());
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
