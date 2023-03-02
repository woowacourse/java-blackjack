package view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public void printPlayerNames(List<String> names) {
        System.out.print(names.get(0) + "와 ");
        String nameMessage = IntStream.range(1, names.size()).mapToObj(i -> names.get(i) + ", ")
                .collect(Collectors.joining());
        System.out.println(nameMessage + "에게 2장을 나누었습니다.");
    }


    public void printCardsPerPlayer(final List<String> namesCopy, final List<List<String>> cardsCardsCopy) {
        printCardsPerDealer(namesCopy.get(0), cardsCardsCopy.get(0));
        for (int i = 1; i < namesCopy.size(); i++) {
            printCurrentPlayerResult(namesCopy.get(i), cardsCardsCopy.get(i));
        }
    }

    private void printCardsPerDealer(final String dealer, final List<String> dealerCards) {
        System.out.println(dealer + ": " + dealerCards.get(0));
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
        System.out.println(name + "카드: " + cardsMessage + " - 결과 : " + cardSum);
    }

    public void printWinningResult(final List<Integer> winningResult, final List<String> namesCopy) {
        System.out.println(System.lineSeparator() + "## 최종 승패");

        int[] result = new int[3];

        for (final Integer integer : winningResult) {
            if (integer == 1) {
                result[0] = result[0] + 1;
                continue;
            }
            if (integer == 0) {
                result[1] = result[1] + 1;
                continue;
            }
            result[2] = result[2] + 1;
        }

        StringBuilder sb = new StringBuilder();
        if (result[0] != 0) {
            sb.append(result[0]).append("승 ");
        }
        if (result[1] != 0) {
            sb.append(result[1]).append("무 ");
        }
        if (result[2] != 0) {
            sb.append(result[2]).append("패");
        }
        System.out.println("딜러: " + sb);

        for (int i = 0; i < winningResult.size(); i++) {
            System.out.print(namesCopy.get(i + 1) + ": ");
            if (winningResult.get(i) == 1) {
                System.out.print("패" + System.lineSeparator());
            }
            if (winningResult.get(i) == 0) {
                System.out.print("무" + System.lineSeparator());
            }
            if (winningResult.get(i) == -1) {
                System.out.print("승" + System.lineSeparator());
            }
        }
    }
}
