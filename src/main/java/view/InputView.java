package view;

import java.util.Scanner;

public class InputView {

    private static final String YES = "y";
    private static final String NO = "n";

    private final Scanner scanner = new Scanner(System.in);

    public String readUsers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public boolean readWillHit(String name) {
        try {
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", name, YES, NO);
            String input = scanner.nextLine().trim().toLowerCase();
            validateHitInput(input);
            return input.equals(YES);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readWillHit(name);
        }
    }

    private void validateHitInput(String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException("[ERROR] " + YES + " 또는 " + NO + "만 입력 가능합니다.");
        }
    }
}
