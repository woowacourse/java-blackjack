package view;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readUserNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(scanner.nextLine().split(",")).toList();
    }

    public boolean readWillHit(String name) {
        try {
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
            String input = scanner.nextLine().trim().toLowerCase();
            validateHitInput(input);
            return input.equals("y");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readWillHit(name);
        }
    }

    private void validateHitInput(String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("[ERROR] y 또는 n만 입력 가능합니다.");
        }
    }

    public int readBetAmount(String name) {
        System.out.println("\n" + name + "의 배팅 금액은?");
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자여야 합니다.");
        }
    }
}
