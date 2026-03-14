package view;

import java.util.List;
import java.util.Scanner;
import util.NameParser;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readParticipants() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> names = NameParser.parse(scanner.nextLine());
        if (names.isEmpty()) {
            throw new IllegalArgumentException("유효한 이름이 입력되지 않았습니다.");
        }
        return names;
    }

    public int readPlayerBettingAmount(String name) {
        try {
            System.out.printf("%n%s의 배팅 금액은?%n", name);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수를 입력해주세요.");
        }
    }

    public boolean checkAddCard(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            return true;
        }
        if (input.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
    }
}
