package blackjack.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import blackjack.model.player.Player;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Map<String, Integer> readUserNamesAndBettingAmount() {
        Map<String, Integer> userNamesAndBettingAmount = new LinkedHashMap<>();
        List<String> userNames = readUserNames();
        for (String userName : userNames) {
            userNamesAndBettingAmount.put(userName, readUserBettingAmount(userName));
        }
        return userNamesAndBettingAmount;
    }

    private List<String> readUserNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> usernames = Arrays.stream(scanner.nextLine().split(","))
                .toList();
        validateUsernameDuplication(usernames);
        return usernames;
    }

    private void validateUsernameDuplication(final List<String> usernames) {
        if (usernames.size() != new HashSet<>(usernames).size()) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }
    }

    private int readUserBettingAmount(final String userName) {
        System.out.println();
        System.out.printf("%s의 배팅 금액은?%n", userName);
        return validateInteger(scanner.nextLine());
    }

    private int validateInteger(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
    }

    public boolean readUserDrawMoreCard(final Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String choice = scanner.nextLine();
        validateChoice(choice);
        return choice.equals("y");
    }

    private void validateChoice(final String choice) {
        if (!choice.equals("y") && !choice.equals("n")) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }

}
