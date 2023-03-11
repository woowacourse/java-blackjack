package view;

import domain.Player;
import domain.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public void printPlayerNames(List<String> names) {
        System.out.print("딜러와 ");
        String nameMessage = IntStream.range(0, names.size()).mapToObj(i -> names.get(i) + ", ")
            .collect(Collectors.joining());
        System.out.println(nameMessage + "에게 2장을 나누었습니다.");
    }


    public void printCardsPerPlayer(final List<String> namesCopy, final List<List<String>> cardsCardsCopy) {
        printCardsPerDealer(cardsCardsCopy.get(0));
        for (int i = 0; i < namesCopy.size(); i++) {
            printCurrentPlayerResult(namesCopy.get(i), cardsCardsCopy.get(i+1));
        }
    }

    private void printCardsPerDealer(final List<String> dealerCards) {
        System.out.println("딜러 : " + dealerCards.get(0));
    }

    public void printCurrentPlayerResult(final String name, final List<String> cards) {
        String cardsMessage = String.join(", ", cards);
        System.out.println(name + "카드: " + cardsMessage);
    }

    public void noticeDealerUnderStandard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerCardResult(final List<String> cards, final int cardSum) {
        String cardsMessage = String.join(", ", cards);
        System.out.println("딜러카드: " + cardsMessage + " - 결과 : " + cardSum);
    }
    public void printAllCardResult(final String name, final List<String> cards, final int cardSum) {
        String cardsMessage = String.join(", ", cards);
        System.out.println(name + "카드: " + cardsMessage + " - 결과 : " + cardSum);
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

    public void noticePlayerCannotReceiveCard(Player player) {
        System.out.println(player.getName() + "는 카드를 추가할 수 없습니다. 현재 총합 : " + player.sumOfParticipantCards());
    }

    public void printEachPlayersProfit(List<Player> players){
        System.out.println("## 최종 수익");
        int finalDealerProfit = sumDelaerProfit(players);
        System.out.println("딜러: " + finalDealerProfit);
        for (Player player : players) {
            System.out.println(player.getName()+": "+player.calculateFinalProfit());
        }
    }

    private static int sumDelaerProfit(List<Player> players) {
        List<Integer> finalProfits = new ArrayList<>();
        players.forEach(
            player -> finalProfits.add(player.calculateFinalProfit())
        );
        int finalDealerProfit = - finalProfits.stream()
                .mapToInt(Integer::intValue).sum();
        return finalDealerProfit;
    }
}
