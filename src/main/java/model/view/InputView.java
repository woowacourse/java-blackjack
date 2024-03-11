package model.view;

import java.util.List;
import java.util.Scanner;
import model.player.Players;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public InputView() {
    }

    public Players requestPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> playerNames = removeBlank(splitName(scanner.nextLine()));
        return Players.from(playerNames);
    }

    private List<String> splitName(String input) {
        return List.of(input.split(","));
    }

    private List<String> removeBlank(List<String> input) {
        return input.stream().map(String::trim).toList();
    }
}
