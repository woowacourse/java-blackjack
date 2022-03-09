package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String INPUT_USERNAME_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public static String[] inputUsersName() {
        System.out.println(INPUT_USERNAME_GUIDE);
        return scanner.nextLine().split(", |,");
    }
}
