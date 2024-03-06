package view;

import domain.PlayerHandsDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OutputView {

    private final String FORM = "%s카드: %s";

    public void printStartDeal(final PlayerHandsDto playerHandsDto) {
        final Set<String> playerNames = playerHandsDto.getPlayerHands().keySet();
        System.out.println("딜러와 " + format(playerNames) + " 에게 2장을 나누었습니다.");
        System.out.println("딜러: "); // TODO: 딜러 카드
        Map<String, List<String>> playerHands = playerHandsDto.getPlayerHands();
        for (Entry<String, List<String>> stringListEntry : playerHands.entrySet()) {
            System.out.printf(FORM, stringListEntry.getKey(), format(stringListEntry.getValue()));
            System.out.println();
        }
    }

    private String format(final Set<String> playerNames) {
        return String.join(", ", playerNames);
    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }
}
