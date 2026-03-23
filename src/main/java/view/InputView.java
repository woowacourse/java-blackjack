package view;

import exception.DuplicateNameException;
import exception.EmptyInputException;
import exception.InvalidBettingAmountFormatException;
import exception.InvalidHitStandInputException;
import java.util.Arrays;
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

        List<String> names = parseNames(input);

        validateEmptyList(names);
        validateDuplicate(names);

        return names;
    }

    public int readBetAmount(String name) {
        printEmptyLine();
        System.out.println(name + "의 배팅 금액은?");
        String input = readLine();
        validateHasInput(input);
        validateIsNumeric(input);
        return Integer.parseInt(input.trim());
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

    private List<String> parseNames(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .toList();
    }

    private static void validateHasInput(String input) {
        if (input == null || input.isBlank()) {
            throw new EmptyInputException();
        }
    }
    
    private void validateEmptyList(List<String> names) {
        if (names.isEmpty()) {
            throw new EmptyInputException();    // 빈 값이 있는 경우에도 예외 반환되도록 수정
        }
    }
    
    private void validateDuplicate(List<String> names) {
        long uniqueCount = names.stream()   //왜 long 이어야 하는가
                .distinct()
                .count();

        if (uniqueCount != names.size()) {
            throw new DuplicateNameException();
        }
    }

    public static void validateIsNumeric(String input) {
        if (!input.matches("-?\\d+")) {
            throw new InvalidBettingAmountFormatException();
        }
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
