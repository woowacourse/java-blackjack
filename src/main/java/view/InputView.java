package view;

import domain.BettingMoney;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String COMMAND_REINPUT_MESSAGE = "y 혹은 n만 입력할 수 있습니다.";
    private static final String COMMAND_INPUT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String PLAYER_NAMES_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BETTING_MONEY_INPUT_MESSAGE = "의 배팅 금액은?";
    private static final String BETTING_MONEY_REINPUT_MESSAGE = "배팅 금액은 0보다 큰 숫자만 입력할 수 있습니다.";
    private static final List<String> CORRECT_COMMAND_INPUT = List.of("y", "n");

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerName() {
        System.out.println(PLAYER_NAMES_INPUT_MESSAGE);
        String input = scanner.nextLine();
        System.out.println();

        return splitPlayerNameInput(input);
    }

    public BettingMoney readBettingMoney(String playerName) {
        while (true) {
            try {
                System.out.println(playerName + BETTING_MONEY_INPUT_MESSAGE);
                String input = scanner.nextLine();
                BettingMoney bettingMoney = new BettingMoney(Integer.parseInt(input));
                System.out.println();
                return bettingMoney;
            } catch (IllegalArgumentException e) {
                System.out.println(BETTING_MONEY_REINPUT_MESSAGE);
            }
        }
    }

    public String readCommand(String playerName) {
        while (true) {
            System.out.println(playerName + COMMAND_INPUT_MESSAGE);
            String input = scanner.nextLine();
            if (CORRECT_COMMAND_INPUT.contains(input)) {
                return input;
            }
            System.out.println(COMMAND_REINPUT_MESSAGE);
        }
    }

    private static List<String> splitPlayerNameInput(String s) {
        if (s == null || s.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(s.split(","))
                .map(String::trim)
                .filter(c -> !c.isEmpty())
                .toList();
    }
}