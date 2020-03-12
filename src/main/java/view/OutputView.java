package view;

import java.util.List;
import java.util.Map;

import domain.Players;
import domain.Result;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.User;

public class OutputView {

    private static final String DELIMITER = ", ";

    public static void initialSetting(Players users) {
        StringBuilder settingInstruction = new StringBuilder();
        List<String> names = users.getUserNames();
        String userNames = String.join(DELIMITER, names);
        settingInstruction.append("딜러와 ").append(userNames).append("에게 2장의 카드를 나누었습니다.");
        System.out.println(settingInstruction);
    }

    public static void printDealerFirstDraw(Dealer dealer) {
        System.out.println(dealer.toStringOneCard());
    }

    public static void printCardStatus(User user) {
        System.out.println(user.toString());
    }

    public static void printCardStatusForAllParticipants(Players participants) {
        for (User user : participants.getPlayers()) {
            printCardStatus(user);
        }
        System.out.println();
    }

    public static void printDealerAdditionalCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    private static void printFinalScore(Participant participant) {
        System.out.println(participant.toString() + " -  결과 : " + participant.calculateScore());
    }

    public static void printFinalScoreForAllParticipants(Dealer dealer, Players users) {
        printFinalScore(dealer);
        for (User user : users.getPlayers()) {
            printFinalScore(user);
        }
    }

    public static void printFinalResult() {
        System.out.println("\n## 최종 승패");

    }

    public static void printDealerResult(Map<String, Result> userResultMap) {
        StringBuilder sb = new StringBuilder("딜러: ");
        int dealerWin = 0;
        int dealerDraw = 0;
        int dealerLose = 0;
        for (Result value : userResultMap.values()) {
            if (value == Result.승) {
                dealerLose++;
            }
            if (value == Result.무) {
                dealerDraw++;
            }
            if (value == Result.패) {
                dealerWin++;
            }
        }
        sb.append(dealerWin).append("승 ");
        sb.append(dealerDraw).append("무 ");
        sb.append(dealerLose).append("패");
        System.out.println(sb);
    }

    public static void printUserResult(Map.Entry<String, Result> userResultEntry) {
        System.out.println(userResultEntry.getKey() + ": " + userResultEntry.getValue());

    }
}
