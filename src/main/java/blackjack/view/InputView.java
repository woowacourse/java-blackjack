package blackjack.view;

import blackjack.domain.money.Money;
import blackjack.exception.BlackJackException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String REQUEST_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    public static final String MORE_DRAW_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    public static final String YES_OR_NO_ERROR = "[ERROR] y 혹은 n만 입력할 수 있습니다.";
    public static final List<String> YES_OR_NO_VALIDATION = new ArrayList<>(
        Arrays.asList("y", "n", "Y", "N"));
    public static final String DELIMITER = ",";
    public static final String OVERLAPPED_PLAYER_NAME_MESSAGE = "[ERROR] 중복되는 이름을 입력할 수 없습니다.";
    public static final String ASK_BET_MONEY = "의 배팅 금액은?";
    public static final String NOT_INTEGER_MESSAGE = "정수가 아닙니다.";
    public static final String PLAYER_MONEY_EXCEPTION_MESSAGE = "판 돈이 0이거나 음수가 되선 안됩니다.";

    private InputView() {
    }

    ;

    public static List<String> requestPlayers() {
        System.out.println(REQUEST_NAME_MESSAGE);
        try {
            return makePlayerName(SCANNER.nextLine());
        } catch (BlackJackException blackJackException) {
            System.out.println(blackJackException.getMessage());
            return requestPlayers();
        }
    }

    public static List<Money> requestCapital(List<String> requestPlayers) {
        List<Money> betCapital = new ArrayList<>();
        for (String requestPlayer : requestPlayers) {
            Money money = requestMoney(requestPlayer);
            betCapital.add(money);
        }
        return betCapital;
    }

    public static boolean requestMoreDraw(String name) {
        System.out.println(name + MORE_DRAW_MESSAGE);
        String input = SCANNER.nextLine();
        try {
            validateYorN(input);
            return translateSignal(input);
        } catch (BlackJackException blackJackException) {
            System.out.println(blackJackException.getMessage());
            return requestMoreDraw(name);
        }
    }

    private static boolean translateSignal(String input) {
        if (input.equals("y")) {
            return true;
        }
        return false;
    }

    private static void validateYorN(String input) {
        if (!YES_OR_NO_VALIDATION.contains(input)) {
            throw new BlackJackException(YES_OR_NO_ERROR);
        }
    }

    private static List<String> makePlayerName(String input) {
        List<String> playerNames = new ArrayList<>();
        for (String value : input.split(DELIMITER, -1)) {
            value = value.trim();
            validateDuplicate(playerNames, value);
            playerNames.add(value);
        }
        return playerNames;
    }

    private static void validateDuplicate(List<String> names, String value) {
        if (names.contains(value)) {
            throw new BlackJackException(OVERLAPPED_PLAYER_NAME_MESSAGE);
        }
    }

    private static Money requestMoney(String name) {
        System.out.println();
        System.out.println(name + ASK_BET_MONEY);
        try {
            int money = receiveRawMoney();
            checkPlayerLowMoney(money);
            return new Money(money);
        } catch (BlackJackException blackJackException) {
            System.out.println(blackJackException.getMessage());
            return requestMoney(name);
        }
    }

    private static int receiveRawMoney() {
        int rawMoney;
        try {
            rawMoney = SCANNER.nextInt();
            SCANNER.nextLine();
            return rawMoney;
        } catch (InputMismatchException inputMismatchException) {
            SCANNER.nextLine();
            System.out.println(NOT_INTEGER_MESSAGE);
            return receiveRawMoney();
        }
    }

    private static void checkPlayerLowMoney(int receiveMoney) {
        if (receiveMoney <= 0) {
            throw new BlackJackException(PLAYER_MONEY_EXCEPTION_MESSAGE);
        }
    }
}
