package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        String[] split = input.split(NAME_DELIMITER);
        return Arrays.asList(split);
    }

    public static String requestDrawCommand(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        return scanner.nextLine();
    }
}
