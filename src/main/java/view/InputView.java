package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public static List<String> readParticipants() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }

    public static boolean checkAddCard(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }
}
