package view;

import exception.EmptyInputException;
import exception.InvalidHitStandInputException;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = readLine();
        validateHasInput(input);
        String[] split = input.split(",");
        return List.of(split);
    }

    public int readBetAmount(String name) {
        printEmptyLine();
        System.out.println(name + "의 배팅 금액은?");
        String input = readLine();
        validateHasInput(input);
        return Integer.parseInt(input);
    }

    public String readHitStand(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = readLine();
        validateHasInput(input);
        if (!(input.equals("y") || input.equals("n"))) {
            throw new InvalidHitStandInputException();
        }
        return input;
    }

    private static void validateHasInput(String input) {
        if (input == null || input.isBlank()) {
            throw new EmptyInputException();
        }
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
