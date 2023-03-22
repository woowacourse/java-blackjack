package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public void printPlayerNames(List<String> names) {
        System.out.print("딜러와 ");
        String nameMessage = IntStream.range(0, names.size()).mapToObj(i -> names.get(i) + ", ")
            .collect(Collectors.joining());
        System.out.println(nameMessage + "에게 2장을 나누었습니다.");
    }


    public void printCardsPerPlayer(final List<String> names, final List<List<String>> cards) {
        printCardsPerDealer(cards.get(0));
        for (int i = 0; i < names.size(); i++) {
            printCurrentPlayerResult(names.get(i), cards.get(i + 1));
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

    public void noticePlayerCannotReceiveCard(String playerName, int sumOfParticipantCards, boolean isPlayerCanAddCard) {
        if (isPlayerCanAddCard){
            System.out.println(playerName + "는 카드를 추가할 수 없습니다. 현재 총합 : " + sumOfParticipantCards);
        }
    }

    public void printDealerProfit(int dealerProfit) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealerProfit);
    }

    public void printEachPlayersProfit(Map<String, Integer> playerWinningResults) {
        for (Entry<String, Integer> playerResult : playerWinningResults.entrySet()) {
            System.out.println(playerResult.getKey() + ": " + playerResult.getValue());
        }
    }
}
