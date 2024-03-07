package blackjack.view;

import java.util.List;

public class OutputView {
    public static void printDistributionSubject(final List<String> names) {
        String formattedName = String.join(", ", names);
        System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", formattedName));
    }
}
