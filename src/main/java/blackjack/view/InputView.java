package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);


    public static List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitNames(SCANNER.nextLine());
    }

    private static List<String> splitNames(final String inputString) {
        return Arrays.asList(inputString.split(","));
    }
}
