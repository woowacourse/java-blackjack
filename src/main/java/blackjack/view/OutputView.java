package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Name;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";

    public static void printDealerCards(Dealer dealer, Map<Name, HoldCards> players) {
        Set<Name> names = players.keySet();
        System.out.println(MessageFormat.format("딜러와 {0}에게 2장의 카드를 나누었습니다.",
            names.stream().map(Name::getValue).collect(Collectors.joining(NAME_DELIMITER))));
        printDealerCards(dealer.getName(), dealer.getHoldCards().getCards());
        for (Name name : players.keySet()) {
            printPlayerCards(name.getValue(), players.get(name).getCards());
        }
        System.out.println();
    }

    public static void printReceivingMoreCardOfDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayerCards(String name, List<Card> cards) {
        System.out.println(MessageFormat.format("{0}카드: {1}", name, cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.joining(NAME_DELIMITER))));
    }

    public static void printDealerCards(String name, List<Card> cards) {
        Card card = cards.get(0);
        System.out.println(MessageFormat
            .format("{0}카드: {1}", name,
                String.join(NAME_DELIMITER, card.getNumber().getName() + card.getSuit().getName())));
    }

    public static void printResult(String name, List<Card> cards, int result) {
        System.out.println(MessageFormat.format("{0}카드: {1} - 결과: {2}", name, cards(cards), result));
    }

    private static String cards(List<Card> cards) {
        return cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.joining(NAME_DELIMITER));
    }

    public static void printGameResult(Map<String, Double> result) {
        int dealer = result.values().stream().mapToInt(Double::intValue).sum() * -1;
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealer);
        for (String name : result.keySet()) {
            System.out.println(name + ": " + result.get(name).intValue());
        }
    }
}
