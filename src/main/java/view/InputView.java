package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return toList(split(scanner.nextLine()));
    }

    public static boolean tryHit(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine().equals("y");
    }

    private static String[] split(final String input) {
        return input.split(",");
    }

    private static List<String> toList(final String[] input) {
        return Arrays.asList(input);
    }
}
