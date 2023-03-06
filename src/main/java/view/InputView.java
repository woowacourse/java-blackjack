package view;

import domain.DrawCommand;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final int MAX_PLAYER_SIZE = 5;

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersName() {
        List<String> playersName = Arrays.asList(scanner.nextLine().split(","));
        validatePlayersSize(playersName);

        return playersName.stream()
                .map(String::strip)
                .collect(Collectors.toList());
    }

    private void validatePlayersSize(List<String> players) {
        if (players.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException("[Error] 플레이어는 최대 5명입니다.");
        }
    }

    public DrawCommand readDrawCommand() {
        return DrawCommand.of(scanner.nextLine());
    }
}
