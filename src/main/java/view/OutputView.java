package view;

import domain.Result;
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

    public void printWinningResult(final List<Result> winningResult, final List<String> namesCopy) {
        System.out.println(System.lineSeparator() + "## 최종 승패");

        printDealerResult(winningResult);

        for (int i = 0; i < winningResult.size(); i++) {
            printPlayerResult(winningResult, namesCopy, i);
        }
    }

    private static void printDealerResult(final List<Result> winningResult) {
        int[] result = getDealerResult(winningResult);
        StringBuilder sb = makeStringOfDealerResult(result);
        System.out.println("딜러: " + sb);
    }

    private static StringBuilder makeStringOfDealerResult(final int[] result) {
        StringBuilder sb = new StringBuilder();
        addResultIfExists(result, sb);
        return sb;
    }

    private static void addResultIfExists(int[] result, StringBuilder sb) {
        if (result[0] != 0) {
            sb.append(result[0]).append("승 ");
        }
        if (result[1] != 0) {
            sb.append(result[1]).append("무 ");
        }
        if (result[2] != 0) {
            sb.append(result[2]).append("패");
        }
    }

    private static void printPlayerResult(final List<Result> winningResult, final List<String> namesCopy, final int i) {
        System.out.print(namesCopy.get(i + 1) + ": ");
        printResultsIfExists(winningResult, i);
    }

    private static void printResultsIfExists(List<Result> winningResult, int i) {
        if (winningResult.get(i) == Result.LOSE) {
            System.out.print("승" + System.lineSeparator());
        }
        if (winningResult.get(i) == Result.DRAW) {
            System.out.print("무" + System.lineSeparator());
        }
        if (winningResult.get(i) == Result.WIN) {
            System.out.print("패" + System.lineSeparator());
        }
    }

    private static int[] getDealerResult(final List<Result> winningResult) {
        int[] results = new int[3];

        winningResult.forEach(result -> makeDealerResult(results, result));
        return results;
    }

    private static void makeDealerResult(final int[] results, final Result result) {
        if (result == Result.WIN) {
            results[0] = results[0] + 1;
            return;
        }
        if (result == Result.DRAW) {
            results[1] = results[1] + 1;
            return;
        }
        results[2] = results[2] + 1;
    }
}
