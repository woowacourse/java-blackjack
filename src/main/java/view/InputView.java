package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NICKNAME_SEPARATOR = ",";
    private static final String LOWER_YES = "y";

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readNicknames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNicknames = scanner.nextLine();

        return Arrays.stream(rawNicknames.split(NICKNAME_SEPARATOR)).toList();
    }

    public int readBettingMoney(String nickname) {
        System.out.println(String.format("%n%s의 배팅 금액은?", nickname));
        String rawBettingMoney = scanner.nextLine();
        validateInteger(rawBettingMoney);

        return Integer.parseInt(rawBettingMoney);
    }

    public boolean readDrawOneMore(String nickname) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", nickname));
        String rawAnswer = scanner.nextLine();
        validateYesOrNo(rawAnswer);

        return LOWER_YES.equalsIgnoreCase(rawAnswer.trim());
    }

    private void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수를 입력해 주세요.");
        }
    }

    private void validateYesOrNo(String input) {
        if (!input.matches("[YyNn]")) {
            throw new IllegalArgumentException("[ERROR] y, n 형태로 입력해 주세요.");
        }
    }
}
