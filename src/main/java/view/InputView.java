package view;

import domain.gamer.AbstractGamer;
import domain.gamer.action.YesNo;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";
    private static final String NO_SPACE = "";
    private static final String SPACE = " ";

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = SCANNER.nextLine().trim();
        validateNames(names);

        return Arrays.stream(names.split(NAME_DELIMITER))
                .map(name -> name.replace(SPACE, NO_SPACE))
                .collect(Collectors.toList());
    }

    private static void validateNames(String names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("이름을 하나 이상 입력해 주세요.");
        }
    }

    public static double inputBettingMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");
        String input = SCANNER.nextLine().trim();
        validateMoneyInput(input);

        return Double.parseDouble(input);
    }

    private static void validateMoneyInput(String input) {
        if (isNumberFormat(input) || input.isEmpty()) {
            throw new IllegalArgumentException("적절한 숫자를 입력해주세요");
        }
    }

    private static boolean isNumberFormat(String input) {
        return input.chars()
                .anyMatch(ch -> !Character.isDigit(ch));
    }

    public static YesNo askDrawMore(AbstractGamer player) {
        System.out.println(player.getName().getValue() + " 는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return YesNo.of(SCANNER.nextLine());
    }
}
