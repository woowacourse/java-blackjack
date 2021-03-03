package blackjack.view;

import blackjack.domain.Card;
import blackjack.view.dto.RoundInitializeStatusDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void showStatus(Map<String, List<Card>> initializeStatus) {
        initializeStatus.keySet()
                .stream()
                .map(key -> String.format("%s: %s", key, initializeStatus.get(key)
                        .stream()
                        .map(card -> card.getCardStatus())
                        .collect(Collectors.joining(", "))))
                .forEach(System.out::println);
    }

    public static void showInitialStatus(RoundInitializeStatusDto statusDto) {
        System.out.println(String.format("%s와 %s에게 2장을 나누었습니다.", statusDto.getDealerName(),
                statusDto.getPlayerNames()
                        .stream()
                        .collect(Collectors.joining(", "))));
        showStatus(statusDto.getStatus());
    }

    public static void showPlayCardStatus(String name, List<Card> cards) {
        String text = String.format("%s: %s", name, cards
                .stream()
                .map(card -> card.getCardStatus())
                .collect(Collectors.joining(", ")));
        System.out.println(text);
    }

    public static void showDealerAddCard(int turnOverCount) {
        System.out.println(String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", turnOverCount));
    }
}
