package view;

import domain.Score;
import domain.TrumpCard;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialCards(Map<String, List<TrumpCard>> playerCards, TrumpCard dealerFirstCard) {
        System.out.println(formatPlayersList(playerCards.keySet().stream().toList()));
        System.out.printf("딜러카드: %s\n", getCardInfo(dealerFirstCard));
        System.out.println(formatPlayerCards(playerCards));
    }

    private String formatPlayersList(List<String> players) {
        return "딜러와 " + String.join(", ", players) + "에게 2장을 나누었습니다.";
    }

    private String formatPlayerCards(Map<String, List<TrumpCard>> playerCards) {
        return playerCards.entrySet().stream()
                .map(entry -> entry.getKey() + "카드: "
                        + getCardInfo(entry.getValue().getFirst()) + ", "
                        + getCardInfo(entry.getValue().getLast()))
                .collect(Collectors.joining("\n"));
    }

    public void printPlayerCards(String name, List<TrumpCard> playerCards) {
        System.out.printf("%s카드:", name);
        System.out.println(playerCards.stream()
                .map(this::getCardInfo)
                .collect(Collectors.joining(",")));
    }

    public void printDealerHitInfo(int dealerHitCount) {
        for (int i = 0; i < dealerHitCount; i++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public void printCardsResult(Map<String, List<TrumpCard>> playerCards, List<TrumpCard> dealerCards) {
        System.out.print("딜러카드: ");
        System.out.print(dealerCards.stream()
                .map(this::getCardInfo)
                .collect(Collectors.joining(",")));
        System.out.printf(" - 결과: %s\n", getScore(dealerCards));

        playerCards.forEach((name, cards) -> {
            System.out.printf("%s카드: ", name);
            System.out.print(cards.stream()
                    .map(this::getCardInfo)
                    .collect(Collectors.joining(",")));
            System.out.printf(" - 결과: %s\n", getScore(cards));
        });
    }

    private String getCardInfo(TrumpCard card) {
        return card.getRank().getTitle() + card.getSuit().getTitle();
    }

    private String getScore(List<TrumpCard> dealerCards) {
        return Score.from(dealerCards).getTitle();
    }
}
