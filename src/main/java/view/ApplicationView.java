package view;

import domain.result.dto.GameResultAnalysis;
import domain.intention.DrawCardIntetion;
import domain.participant.ParticipantName;
import domain.participant.dto.ParticipantHand;
import domain.participant.dto.ParticipantResult;

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

    public void printAllPlayersHand(List<ParticipantHand> playerHands) {
        for (ParticipantHand playerHand : playerHands) {
            printParticipantHand(playerHand);
        }
    }

    public void printParticipantHand(ParticipantHand playerHand) {
        writer.printAllParticipantsHand(playerHand.playerName(), formatHands(playerHand.handOnCards()));
    }

    public void printDealerAdditionalDrawCardMessage() {
        writer.printDealerAdditionalDrawCardMessage();
    }

    public void printFinalResultMessage(GameResultAnalysis resultAnalysis) {
        writer.printFinalResultTitleMessage();
        printAllPlayersResult(resultAnalysis);
    }

    private void printAllPlayersResult(GameResultAnalysis resultAnalysis) {
        writer.printFinalResultOfDealer(resultAnalysis.dealerGameResult().resultStatistic());
        resultAnalysis.playerGameResults().forEach(player ->
                writer.printFinalResultOfPlayer(player.playerName(), player.gameResult().displayName())
        );
    }

    public void printFinalResultMessage(ParticipantResult playerResult) {
        ParticipantHand playerHand = playerResult.playerHand();
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
