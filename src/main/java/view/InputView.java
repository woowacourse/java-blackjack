package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        String response = prompt("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(response.split(",")).toList();
    }

    public static boolean readAddPlayerCard(String name) {
        String response = prompt(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
        return response.equals("y");
    }

    private static String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
