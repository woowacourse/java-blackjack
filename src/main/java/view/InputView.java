package view;

import static view.reader.Console.readLine;

import controller.Continuation;
import java.util.Arrays;
import java.util.List;
import model.BettingMoney;

public class InputView {
    private static final long BETTING_UNIT = 100;

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        while (true) {
            try {
                String input = readLine();
                checkInputPlayers(input);
                return Arrays.asList(input.split(","));
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public static BettingMoney readPlayerBettingMoney(String playerName) {
        System.out.println("\n" + playerName + "의 베팅 금액은? (최소 베팅금액: 100, 최대 베팅금액: 10억, 베팅 단위: 100");
        while (true) {
            try {
                String input = readLine();
                checkInputIsNumber(input);
                return new BettingMoney(Long.parseLong(input));
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public static String readMoreCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        while (true) {
            try {
                String input = readLine();
                Continuation.from(input); // 여기서 검증
                return input;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private static void checkInputPlayers(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력이 비어있습니다. 다시 입력해주세요.");
        }
        if (!input.matches("^[a-zA-Z0-9]+(,[a-zA-Z0-9]+)*$")) {
            throw new IllegalArgumentException("올바른 입력 형식이 아닙니다. 다시 입력해주세요.");
        }
    }

    private static void checkInputIsNumber(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력이 비어있습니다. 다시 입력해주세요.");
        }
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }
}
