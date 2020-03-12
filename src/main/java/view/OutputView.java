package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Result;
import domain.player.Dealer;
import domain.player.Player;
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

    public static void printFinalScore(Player player) {
        System.out.println(player.toString() + " -  결과 : " + player.calculateScore());
    }

    public static void printFinalScoreForAllParticipants(Dealer dealer, List<User> users) {
        printFinalScore(dealer);
        for (User user : users) {
            printFinalScore(user);
        }
    }

    public static void printFinalResult() {
        System.out.println("\n## 최종 승패");

    }

    public static void printDealerResult(int dealerWin, int dealerDraw, int dealerLose) {
        System.out.println("딜러: " + dealerWin + "승 " + dealerDraw + "무 " + dealerLose + "패");
    }

    public static void printUserResult(Map.Entry<String, Result> userResultEntry) {
        System.out.println(userResultEntry.getKey() + ": " + userResultEntry.getValue());

    }
}
