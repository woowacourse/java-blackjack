package view;

import domain.dto.NameAndCards;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printInitialCards(NameAndCards dealer, List<NameAndCards> participants) {
        List<String> names = participants.stream()
                .map(NameAndCards::name)
                .toList();

        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.name(), String.join(", ", names));

        System.out.printf("%s카드: %s%n",
                dealer.name(),
                String.join(", ", dealer.cards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()));

        participants.forEach(OutputView::printPlayerCards);
    }

    public static void printPlayerCards(NameAndCards player) {
        System.out.printf("%s카드: %s%n",
                player.name(),
                String.join(", ", player.cards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()));
    }

    public static void printPlayerCardsAndSum(NameAndCards dealer, List<NameAndCards> participants,
                                              Map<String, Integer> nameAndSum) {
        System.out.printf("%s카드: %s - 결과: %d%n",
                dealer.name(),
                String.join(", ", dealer.cards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()),
                nameAndSum.get(dealer.name()));
        participants.forEach(player -> {
            System.out.printf("%s카드: %s - 결과: %d%n",
                    player.name(),
                    String.join(", ", player.cards().stream()
                            .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                    card.getCardShape().getTitle()))
                            .toList()),
                    nameAndSum.get(player.name()));
        });
    }

    public static void printAddCardToDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
