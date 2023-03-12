package view;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView2 {

        public void printGameStarted(Map<String, List<String>> participants){
                var nameJoiner = new StringJoiner(", ");
                for (String name : participants.keySet()) {
                        nameJoiner.add(name);
                }
                System.out.printf("%s에게 2장을 나누었습니다.\n", nameJoiner);

                for (var entry : participants.entrySet()) {
                        System.out.println(entry.getKey() + "카드 : " + joinCard(entry.getValue()));
                }
        }

        private StringJoiner joinCard(List<String> cards) {
                var cardJoiner = new StringJoiner(", ");
                for (String card : cards) {
                        cardJoiner.add(card);
                }
                return cardJoiner;
        }

//
//        printNameAndCard(dealer);
//        for (PlayerDto player : players) {
//            printNameAndCard(player);
//        }
}
