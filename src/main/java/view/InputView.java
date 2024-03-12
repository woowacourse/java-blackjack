package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        validatePlayers(input);
        return Arrays.stream(input.split(",")).map(String::trim).toList();
    }

    private static void validatePlayers(final String input) {
        validateEmptiness(input);
        validateEdgeDelimiter(input);
    }

    private static void validateEdgeDelimiter(final String input) {
        if (input.startsWith(",")) {
            throw new IllegalArgumentException("입력은 구분자로 시작할 수 없습니다.");
        }
        if (input.endsWith(",")) {
            throw new IllegalArgumentException("입력은 구분자로 끝날 수 없습니다.");
        }
    }

    private static void validateEmptiness(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력은 비어 있을 수 없습니다.");
        }
    }

    public static boolean tryHit(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        final String hit = scanner.nextLine();
        validateHit(hit);
        return hit.equals("y");
    }

    private static void validateHit(final String hit) {
        if (isValidHit(hit)) {
            throw new IllegalArgumentException("y/n만 입력해주세요.");
        }
    }

    private static boolean isValidHit(final String hit) {
        return !hit.equals("y") && !hit.equals("n");
    }
}
