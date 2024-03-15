package blackjack.view;

import blackjack.domain.Profit;
import blackjack.domain.player.Name;
import blackjack.domain.result.Result;

public class ResultView {
    public static void printResult(Result result) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        result.getResult()
              .forEach(ResultView::printPlayerResult);
    }

    private static void printPlayerResult(Name name, Profit profit) {
        System.out.println(String.format("%s: %s", name.asString(), profit.getValue()));
    }
}
