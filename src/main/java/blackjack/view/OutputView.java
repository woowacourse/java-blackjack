package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.vo.Name;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final int FIRST_DEALER_CARD = 0;

    public static void printAllCards(Dealer dealer, Map<Name, List<Card>> players) {
        System.out.println(MessageFormat.format("딜러와 {0}에게 2장의 카드를 나누었습니다.", toNames(players)));
        System.out.println();
        printCard(dealer.getName(), Collections.singletonList(dealer.getHoldCards().getCards().get(FIRST_DEALER_CARD)));
        for (Name name : players.keySet()) {
            printCard(name.getValue(), players.get(name));
        }
        System.out.println();
    }

    public static void printDealerHitCount(int hitCount) {
        System.out.println();
        System.out.println(MessageFormat.format("딜러는 16이하라 {0}장의 카드를 더 받았습니다.", hitCount));
        System.out.println();
    }

    public static void printCard(String name, List<Card> cards) {
        System.out.println(MessageFormat.format("{0}카드: {1}", name, cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.joining(NAME_DELIMITER))));
    }

    public static void printResult(String name, List<Card> cards, int result) {
        System.out.println(MessageFormat.format("{0}카드: {1} - 결과: {2}", name, cards(cards), result));
    }

    public static void printGameResult(Map<String, Double> result) {
        int dealer = result.values().stream().mapToInt(Double::intValue).sum() * -1;
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealer);
        for (String name : result.keySet()) {
            System.out.println(name + ": " + result.get(name).intValue());
        }
    }

    private static String toNames(Map<Name, List<Card>> players) {
        return players.keySet().stream()
            .map(Name::getValue)
            .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String cards(List<Card> cards) {
        return cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.joining(NAME_DELIMITER));
    }
}
