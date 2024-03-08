package view;

import domain.gamer.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        String[] playerNames = input.split(",");
        return Arrays.stream(playerNames).map(String::trim).toList();
    }

    public static String readSelectionOf(final Player player) {
        String message = String.format(System.lineSeparator() + "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                player.getName().getValue());
        System.out.println(message);
        return scanner.nextLine().trim();
    }
}
