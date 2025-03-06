package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_NAME_DELIMITER = ", ";

    public void printHandOutIntroduce(final List<String> playerNames) {
        final String names = playerNames.stream().collect(Collectors.joining(PLAYER_NAME_DELIMITER));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), names);
    }

    public void printDealerHandOutResult(final String card) {
        System.out.printf("딜러카드: %s" + System.lineSeparator(), card);
    }

    public void printPlayersCard(final List<Map.Entry<String, List<String>>> players) {
        players.forEach(player -> printPlayerCards(player.getKey(), player.getValue()));
    }

    public void printPlayerCards(final String name, final List<String> card) {
        final String cards = card.stream().collect(Collectors.joining(PLAYER_NAME_DELIMITER));
        System.out.printf("%s카드: %s" + System.lineSeparator(), name, cards);
    }

    public void printDealerPickCard() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }
}
