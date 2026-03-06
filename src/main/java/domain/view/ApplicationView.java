package domain.view;

import domain.answer.Answer;
import domain.player.PlayerName;
import domain.player.dto.PlayerHandDto;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ApplicationView {

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
            return Arrays.stream(names.split(","))
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
        String formattedNames = String.join(",", playerNames);
        writer.printDealInitialCardMessage(formattedNames);
    }

    public void printAllParticipantsHand(List<PlayerHandDto> playerHands) {
        for (PlayerHandDto playerHand : playerHands) {
            printParticipantHand(playerHand);
        }
    }

    public void printParticipantHand(PlayerHandDto playerHand) {
        writer.printAllParticipantsHand(playerHand.playerName(), joining(playerHand.handOnCards()));
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

    private String joining(List<String> strings) {
        return String.join(", ", strings);
    }

}
