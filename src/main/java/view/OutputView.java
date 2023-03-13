package view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    public void printPlayerNames(List<String> names) {
        System.out.print(names.get(0) + "와 ");
        String nameMessage = IntStream.range(1, names.size()).mapToObj(names::get)
                .collect(Collectors.joining(", "));
        System.out.println(nameMessage + "에게 2장을 나누었습니다.");
    }


    public void printCardsPerPlayer(final List<String> namesCopy, final List<List<String>> cardsCardsCopy) {
        for (int i = 0; i < namesCopy.size(); i++) {
            printCurrentPlayerResult(namesCopy.get(i), cardsCardsCopy.get(i));
        }
    }

    public void printCardsPerDealer(final String dealerFirstCard) {
        System.out.println("딜러: " + dealerFirstCard);
    }

    public void printCurrentPlayerResult(final String name, final List<String> cards) {
        String cardsMessage = String.join(", ", cards);
        System.out.println(name + "카드: " + cardsMessage);
    }

    public void noticeDealerUnderStandard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printAllCardResult(final String name, final List<String> cards, final int cardSum) {
        String cardsMessage = String.join(", ", cards);
        if (cardSum > 21) {
            System.out.println(name + "카드: " + cardsMessage + " - 결과 : 버스트");
            return;
        }
        System.out.println(name + "카드: " + cardsMessage + " - 결과 : " + cardSum);
    }

    public void printDealerGameResult(final Integer profitMoney) {
        System.out.println(NEW_LINE + "## 최종 수익");
        System.out.println("딜러: " + profitMoney);
    }

    public void printPlayersGameResult(final List<String> namesCopy, final List<Integer> monies) {
        for (int index = 0; index < namesCopy.size(); index++) {
            System.out.println(namesCopy.get(index) + ": " + monies.get(index));
        }
    }

//    private void printDealerResult(final List<Integer> monies) {
//        int sum = sumDealerResult(monies);
//        System.out.println("딜러: " + (sum * -1));
//    }

//    private int sumDealerResult(final List<Integer> monies) {
//        return monies.stream()
//                .mapToInt(i -> i)
//                .sum();
//    }

    public void newLine() {
        System.out.print(NEW_LINE);
    }

    public void printPlayerNameForBetting(final String playerName) {
        System.out.println(playerName + "의 베팅 금액은?");
    }

    public void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public void printAllDealerCardResult(final List<String> dealerCards, final int sumOfDealerCards) {
        printAllCardResult("딜러", dealerCards, sumOfDealerCards);
    }
}
