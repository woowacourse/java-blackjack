package domain.view;

import domain.analyzer.dto.ResultAnalysisDto;
import domain.answer.Answer;
import domain.player.PlayerName;
import domain.player.dto.PlayerHandDto;
import domain.player.dto.PlayerResultDto;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ApplicationView {

    private static final String PLAYER_NAME_DELIMITER = ",";

    public InputReader reader;
    public OutputWriter writer;

    public ApplicationView(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public List<PlayerName> requestPlayerNames() {
        return retry(() -> {
            writer.printInputNameGuideMessage();
            String names = reader.readInput();
            return Arrays.stream(names.split(PLAYER_NAME_DELIMITER))
                    .map(String::trim)
                    .map(PlayerName::from)
                    .toList();
        });
    }

    public Answer askDrawCard(String playerName) {
        return retry(() -> {
            writer.printDrawCardGuideMessage(playerName);
            String input = reader.readInput();
            return Answer.from(input);
        });
    }

    public void printFirstHandOutResult(List<String> playerNames) {
        String formattedNames = String.join(PLAYER_NAME_DELIMITER, playerNames);
        writer.printDealInitialCardMessage(formattedNames);
    }

    public void printAllParticipantsHand(List<PlayerHandDto> playerHands) {
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
        writer.printFinalResultOfDealer(resultAnalysis.getDealerResult());
        printAllPlayersResult(resultAnalysis);
    }

    private void printAllPlayersResult(ResultAnalysisDto resultAnalysis) {
        resultAnalysis.playerGameResults()
                .forEach(player -> {
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
