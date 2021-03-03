package blackjack.view;

import blackjack.domain.Card;

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
}
