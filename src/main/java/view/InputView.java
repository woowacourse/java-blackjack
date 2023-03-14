package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String NAME_DELIMITER = ",";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = scanner.nextLine();
        return asList(rawNames);
    }

    private List<String> asList(String rawNames) {
        return Arrays.stream(rawNames.split(NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public int readBettingMoney(final String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
        String rawMoney = scanner.nextLine();
        validateIsInteger(rawMoney);
        return Integer.parseInt(rawMoney);
    }

    private void validateIsInteger(String rawMoney) {
        try {
            Integer.parseInt(rawMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅금은 정수로만 입력하여야 합니다.");
        }
    }

    public String readDecision(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine()
                .trim()
                .toLowerCase();
    }
}
