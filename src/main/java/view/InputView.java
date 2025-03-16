package view;

import domain.participant.Participant;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String PLAYER_NAMES_INPUT_REGEX = "^([가-힣a-zA-Z]+)(,\s*[가-힣a-zA-Z]+)*$";
    private static final String INVALID_PLAYER_NAMES_INPUT = "이름 입력 형식이 올바르지 않습니다.";
    private static final String INVALID_YN_INPUT = "yn 입력 형식이 올바르지 않습니다.";
    private static final String ASK_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String YN_REGEX = "^[yYnN]$";
    private static final String ASK_ONE_MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String ASK_PLAYER_BET_AMOUNT_MESSAGE = "%s의 베팅 금액은?\n";
    private static final String INVALID_BET_AMOUNT_INPUT = "양수로 베팅 금액을 입력해주세요.";

    private static final Scanner sc = new Scanner(System.in);

    public static String getPlayerNamesInput() {
        System.out.println(ASK_PLAYER_NAMES_MESSAGE);
        String playerNamesInput = sc.nextLine();
        validatePlayerNames(playerNamesInput);
        return playerNamesInput;
    }

    public static int getBetAmountInput(String playerName) {
        System.out.printf(ASK_PLAYER_BET_AMOUNT_MESSAGE,  playerName);
        String betAmountInput = sc.nextLine();
        try {
            int parsedBetAmount = Integer.parseInt(betAmountInput);
            validateBetAmount(parsedBetAmount);
            return parsedBetAmount;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_INPUT);
        }
    }

    public static String getYnInput(Participant participant) {
        System.out.println(participant.getName() + ASK_ONE_MORE_CARD_MESSAGE);
        String ynInput = sc.nextLine();
        if (!Pattern.matches(YN_REGEX, ynInput)) {
            throw new IllegalArgumentException(INVALID_YN_INPUT);
        }
        return ynInput;
    }

    private static void validatePlayerNames(final String playerNamesInput) {
        if (!Pattern.matches(PLAYER_NAMES_INPUT_REGEX, playerNamesInput)) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAMES_INPUT);
        }
    }

    private static void validateBetAmount(final int parsedBetAmount) {
        if (parsedBetAmount <= 0) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_INPUT);
        }
    }
}
