package domain.view;

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
