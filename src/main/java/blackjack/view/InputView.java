package blackjack.view;

import blackjack.controller.dto.UserNameDTO;
import blackjack.domain.player.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String DELIMITER = ",";

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<String> usersNames = getUsersNames();
        for (String userName : usersNames) {
            System.out.println(userName + "의 배팅 금액은?");
            String bettingMoneyInput = SCANNER.nextLine();
            System.out.println();
            users.add(new User(userName, Integer.parseInt(bettingMoneyInput)));
        }
        return users;
    }

    private static List<String> getUsersNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String value = SCANNER.nextLine();
        System.out.println();
        return Arrays.asList(value.split(DELIMITER));
    }

    public static String getYesOrNo(UserNameDTO userNameDTO) {
        System.out.println(userNameDTO.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return SCANNER.nextLine();
    }
}
