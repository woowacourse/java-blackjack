package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readNicknames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNicknames = scanner.nextLine();
        return Arrays.stream(rawNicknames.split(",")).toList();
    }

    public static boolean readDrawOneMore(String nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", nickname);
        String rawAnswer = scanner.nextLine();
        return "y".equalsIgnoreCase(rawAnswer.trim());
    }
}
