package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> scanPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.");
        return Arrays.asList(scanner.nextLine().split(","));
    }
}
