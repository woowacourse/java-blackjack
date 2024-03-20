package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return trimAndToList(split(scanner.nextLine()));
    }

    public static int inputBetAmount(final String name) {
        System.out.println(name + "의 배팅 금액은?");
        return toInt(scanner.nextLine());
    }

    private static int toInt(final String input) {
        return Integer.parseInt(input);
    }

    public static String inputHitCommand(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }

    private static String[] split(final String input) {
        return input.split(",");
    }

    private static List<String> trimAndToList(final String[] input) {
        return Arrays.stream(input).sequential().map(String::trim).toList();
    }
}
