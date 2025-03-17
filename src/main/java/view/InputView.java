package view;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    private static final String NAME_DELIMITER = ",";

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        return Arrays.stream(scanner.nextLine().split(NAME_DELIMITER)).toList();
    }

    public int getBetAmount(String name) {
        System.out.println(name + "의 배팅 금액은?");
        try {
            int betAmount = scanner.nextInt();
            scanner.nextLine();
            return betAmount;
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("올바른 금액을 입력해야 합니다.");
        }
    }

    public String askReceive(String name) {
        System.out.println(name + "는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
