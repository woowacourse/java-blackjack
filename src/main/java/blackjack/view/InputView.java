package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String USER_NAME_EXCEPTION_MESSAGE = "유저의 이름이 잘못되었습니다.";
    private static final String GAME_START_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputUsers() {
        System.out.println(GAME_START_MESSAGE);
        String users = scanner.nextLine();
        return validateUsersName(users);
    }

    private static List<String> validateUsersName(String users) {
        String[] usersName = users.split(",");
        if (Arrays.stream(usersName)
                .anyMatch(String::isBlank)) {
            throw  new IllegalArgumentException(USER_NAME_EXCEPTION_MESSAGE);
        }
        return Arrays.stream(usersName)
                .collect(Collectors.toList());
    }
}
