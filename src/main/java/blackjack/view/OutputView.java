package blackjack.view;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private OutputView() {
    }

    public static void printSettingCardsResult(String userName, List<String> cards) {
        System.out.print(userName + "카드: ");

        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String card : cards) {
            stringJoiner.add(card);
        }

        System.out.println(stringJoiner);
    }

}
