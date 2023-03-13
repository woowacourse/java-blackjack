package view;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

        public void printGameStarted(Map<String, List<String>> participants){
                var nameJoiner = new StringJoiner(", ");
                for (String name : participants.keySet()) {
                        nameJoiner.add(name);
                }
                System.out.printf("%s에게 2장을 나누었습니다.\n", nameJoiner);

                for (var entry : participants.entrySet()) {
                        printNameAndCards(entry.getKey(), entry.getValue());
                }
        }

        public void printNameAndCards(String name, List<String> cards) {
                System.out.println(name + "카드 : " + joinCard(cards));
        }

        private StringJoiner joinCard(List<String> cards) {
                var cardJoiner = new StringJoiner(", ");
                for (String card : cards) {
                        cardJoiner.add(card);
                }
                return cardJoiner;
        }

        public void printHitDealer() {
                System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }

        public void printResult(String name, List<String> cardString, int value) {
                System.out.println(name + "카드 : " + joinCard(cardString) + " - 결과: " + value);
        }

        public void printProfit(Map<String, Integer> profitMap) {
                System.out.println("## 최종 수익");
                for (Map.Entry<String, Integer> profit : profitMap.entrySet()) {
                        System.out.println(profit.getKey() + " : " + profit.getValue());
                }
        }

}
