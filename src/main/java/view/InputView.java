package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String inputUsers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public String inputYesOrNo(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
            return answer;
        }
        throw new IllegalArgumentException("y또는 n를 제외한 입력은 받지 않습니다");
    }

    public long inputBettingMoney(final String name) {
        System.out.printf("%s의 배팅 금액은?\n", name);
        long money = 0;
        try {
            money = scanner.nextLong();
        } catch (InputMismatchException inputMismatchException) {
            throw new IllegalArgumentException("숫자가 아닌 값은 입력할 수 없습니다.");
        }
        return money;
    }

    public void close() {
        scanner.close();
    }
}
