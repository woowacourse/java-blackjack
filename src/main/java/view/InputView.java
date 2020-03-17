package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final int MONEY_UNIT = 1000;
    private static final int NONE_EXCHANGE = 0;

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNamesValue = scanner.nextLine();
        List<String> playerNames = Arrays.asList(playerNamesValue.split(DELIMITER));

        for(String playerName : playerNames) {
            if(isNotAlphabet(playerName)) {
                throw new IllegalArgumentException();
            }
        }
        return playerNames;
    }

    public static List<Integer> inputPlayerMoney(List<String> playerNames) {
        List<Integer> playerMoneys = new ArrayList<>();

        for(int playerIndex = 0; playerIndex < playerNames.size(); playerIndex++) {
            System.out.println(String.format("%s의 배팅 금액은?",playerNames.get(playerIndex)));
            String playerMoney = scanner.nextLine();
            if(isNotMoney(playerMoney)) {
                throw new IllegalArgumentException("1000원 단위의 배팅 금액을 입력해주세요");
            }
            playerMoneys.add(Integer.parseInt(playerMoney));
        }

        return playerMoneys;
    }

    private static boolean isNotMoney(String playerMoney) {
        return playerMoney.chars()
                .anyMatch(c -> !Character.isDigit(c)) || Integer.parseInt(playerMoney) % MONEY_UNIT != NONE_EXCHANGE;
    }

    public static String inputGetMoreCard(String name) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?",name));
        String input = scanner.nextLine();

        if(!isYesOrNo(input)) {
            throw new IllegalArgumentException();
        }

        return input;
    }

    private static boolean isYesOrNo(String input) {
        return YES.equals(input) || NO.equals(input);
    }

    private static boolean isNotAlphabet(String playerNamesValue) {
        return playerNamesValue.chars()
                .anyMatch(c -> !Character.isAlphabetic(c));
    }
}
