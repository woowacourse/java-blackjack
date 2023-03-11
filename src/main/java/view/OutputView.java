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

    public void printCardsPerDealer(final String dealerName, final String dealerFirstCard) {
        System.out.println(dealerName + ": " + dealerFirstCard);
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

    public void printWinningResult(final List<Integer> winningResult, final List<String> namesCopy) {
        System.out.println(NEW_LINE + "## 최종 승패");

        printDealerResult(winningResult);

        for (int i = 0; i < winningResult.size(); i++) {
            printResult(winningResult, namesCopy, i);
        }
    }

    private static void printDealerResult(final List<Integer> winningResult) {
        int[] result = getDealerResult(winningResult);
        StringBuilder sb = makeStringOfDealerResult(result);
        System.out.println("딜러: " + sb);
    }

    private static StringBuilder makeStringOfDealerResult(final int[] result) {
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
        return sb;
    }

    private static void printResult(final List<Integer> winningResult, final List<String> namesCopy, final int i) {
        System.out.print(namesCopy.get(i) + ": ");
        if (winningResult.get(i) == 1) {
            System.out.print("패" + NEW_LINE);
        }
        if (winningResult.get(i) == 0) {
            System.out.print("무" + NEW_LINE);
        }
        if (winningResult.get(i) == -1) {
            System.out.print("승" + NEW_LINE);
        }
    }

    private static int[] getDealerResult(final List<Integer> winningResult) {
        int[] result = new int[3];

        winningResult.forEach(integer -> makeDealerResult(result, integer));
        return result;
    }

    private static void makeDealerResult(final int[] result, final Integer integer) {
        if (integer == 1) {
            result[0] = result[0] + 1;
            return;
        }
        if (integer == 0) {
            result[1] = result[1] + 1;
            return;
        }
        result[2] = result[2] + 1;
    }

    public void newLine() {
        System.out.print(NEW_LINE);
    }

    public void printPlayerNameForBetting(final String playerName) {
        System.out.println(playerName + "의 베팅 금액은?");
    }

    public void printErrorMessage(final String message) {
        System.out.println(message);
    }
}
