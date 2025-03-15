package view;

import domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        InputValidator.validateNameFormat(input);
        String[] split = input.split(",");
        return Arrays.stream(split)
            .toList();
    }

    public static boolean askForOneMoreCard(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getName());
        String input = scanner.nextLine();
        return Answer.selectAnswer(input);
    }

    public static int readBetAmount(String playerName) {
        System.out.printf("%s의 배팅 금액은?%n", playerName);
        String input = scanner.nextLine();
        InputValidator.validateBetAmount(input);
        return Integer.parseInt(input);
    }
}
