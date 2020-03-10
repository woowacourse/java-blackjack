package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.player.Dealer;
import domain.player.User;

public class OutputView {

    public static final String DELIMITER = ", ";

    public static void initialSetting(List<User> users) {
        StringBuilder settingInstruction = new StringBuilder();
        List<String> names = users.stream().map(x -> x.getName()).collect(Collectors.toList());
        String userNames = String.join(DELIMITER, names);
        settingInstruction.append("딜러와 ").append(userNames).append("에게 2장의 카드를 나누었습니다.");
        System.out.println(settingInstruction);
    }

    public static void printOneCard(Dealer dealer) {
        System.out.println(dealer.toStringOneCard());
    }

    public static void printCardStatus(User user) {
        System.out.println(user.toString());
    }

    public static void printDealerAdditionalCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }
}
