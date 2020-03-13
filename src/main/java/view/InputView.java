package view;

import domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(scanner.nextLine()
                .replace(" ", "")
                .split(","));
    }

    public static boolean inputMoreCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("y")) {
            return true;
        }
        if (input.toLowerCase().equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("Not valid input (only y/n)");
    }
}
