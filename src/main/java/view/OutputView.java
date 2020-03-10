package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.User;

public class OutputView {

    public static final String DELIMITER = ", ";

    public static void initialSetting(List<User> users) {
        StringBuilder settingInstruction = new StringBuilder();
        List<String> names = users.stream().map(x -> x.getName()).collect(Collectors.toList());
        String userNames = String.join(DELIMITER, names);
        settingInstruction.append("딜러와 ").append(userNames).append("에게 2장의 카드를 나누었습니다.");
        System.out.println(settingInstruction);
    }
}
