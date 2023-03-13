package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String ENTER_PLAYER_NAME_NOTICE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ENTER_ADD_CARD_NOTICE = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String ENTER_BETTING_AMOUNT_NOTICE = "\n%s의 배팅 금액은?\n";
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final int MIN_PLAYER_NUM = 1;
    private static final int MAX_PLAYER_NUM = 10;


    private final Scanner scanner = new Scanner(System.in);

    public List<String> enterNames() {
        System.out.println(ENTER_PLAYER_NAME_NOTICE);
        String input = scanner.nextLine();

        List<String> playerNames = splitWithComma(input);
        validatePlayerSize(playerNames.size());

        return playerNames;
    }

    private List<String> splitWithComma(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    private void validatePlayerSize(int size) {
        if (size < MIN_PLAYER_NUM || size > MAX_PLAYER_NUM) {
            throw new IllegalArgumentException("플레이어는 1명 이상 10명 이하여야 합니다.");
        }
    }

    public boolean askForAnotherCard(String name) {
        System.out.printf(ENTER_ADD_CARD_NOTICE, name);
        String input = scanner.nextLine().strip();
        validateResponse(input);

        return isYes(input);
    }

    private void validateResponse(String response) {
        if (!(response.equals(NO) || response.equals(YES))) {
            throw new IllegalArgumentException("추가 카드에 대한 응답은 y와 n으로 해야합니다.");
        }
    }

    private boolean isYes(String input) {
        return input.equals(YES);
    }

    public int enterBettingAmount(String name) {
        System.out.printf(ENTER_BETTING_AMOUNT_NOTICE, name);
        String input = scanner.nextLine();

        validateNumeric(input);

        return Integer.parseInt(input);
    }

    private void validateNumeric(String bettingAmount) {
        try {
            Integer.parseInt(bettingAmount);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            throw new IllegalArgumentException("배팅 금액으로 숫자를 입력해야합니다.");
        }
    }

}
