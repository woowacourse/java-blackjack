package domain.view;

import domain.analyzer.dto.ResultAnalysisDto;
import domain.answer.DrawCardIntetion;
import domain.player.PlayerName;
import domain.player.dto.PlayerHandDto;
import domain.player.dto.PlayerResultDto;

import java.util.List;
import java.util.function.Supplier;

public class ApplicationView {

    private final String DELIMITER = ",";

    private final InputReader reader;
    private final OutputWriter writer;

    private ApplicationView(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static ApplicationView of(InputReader reader, OutputWriter writer) {
        return new ApplicationView(reader, writer);
    }

    public List<PlayerName> requestPlayerNames() {
        return retry(this::readPlayerNames);
    }

    private List<PlayerName> readPlayerNames() {
        writer.printInputNameGuideMessage();
        List<String> names = reader.readInputBasedOnSeparator(DELIMITER);

        return names.stream().map(PlayerName::from).toList();
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

    public void printFinalResultMessage(ResultAnalysisDto resultAnalysis) {
        writer.printFinalResultTitleMessage();
        printAllPlayersResult(resultAnalysis);
    }

    private void printAllPlayersResult(ResultAnalysisDto resultAnalysis) {
        writer.printFinalResultOfDealer(resultAnalysis.getDealerResult());
        resultAnalysis.playerGameResults().forEach(player -> {
            writer.printFinalResultOfPlayer(player.playerName(), player.gameResult().displayName());
        });
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
