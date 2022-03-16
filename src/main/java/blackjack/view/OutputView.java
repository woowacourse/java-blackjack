package blackjack.view;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Player;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_BUST_MESSAGE = "- %s은(는) %d 점으로 버스트 됐습니다.";
    private static final String TOTAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String WIN_DRAW_LOSE_STATUS_MESSAGE = "%s: %s";
    private static final String HIT_RESULT_MESSAGE = "%s: %s - 결과: %d";
    private static final String CARD_JOINING_DELIMITER = ", ";
    private static final String STATUS_DELIMITER = ": ";

    public static void printInitCard(Map<String, Cards> cardStatus) {
        cardStatus.forEach((key, value) -> System.out.println(key + STATUS_DELIMITER + joinCardString(value)));
    }

    public static void printPresentStatus(Player player) {
        System.out.println();
        StringBuilder messageBuilder = new StringBuilder(
                String.format(WIN_DRAW_LOSE_STATUS_MESSAGE, player.getName(), joinCardString(player.getCards())));
        if (player.isBust()) {
            messageBuilder.append(
                    String.format(PLAYER_BUST_MESSAGE, player.getName(), player.getCards().calculateScore()));
        }
        System.out.println(messageBuilder);
    }

    public static void printCardResults(Map<String, Cards> hitResults) {
        System.out.println();
        hitResults.forEach((playerName, cards) -> System.out.printf(HIT_RESULT_MESSAGE + "\n", playerName,
                joinCardString(cards), cards.calculateScore()));
    }

    private static String joinCardString(Cards cards) {
        return cards.getCardValues()
                .stream()
                .map(card -> card.getDenomination().getName() + card.getSuit().getName())
                .collect(Collectors.joining(CARD_JOINING_DELIMITER));
    }

    public static void printResult(Map<String, String> resultStrings) {
        System.out.println();
        System.out.println(TOTAL_RESULT_MESSAGE);
        resultStrings.forEach((playerName, winDrawLose) -> System.out.printf(WIN_DRAW_LOSE_STATUS_MESSAGE + "\n",
                playerName,
                winDrawLose));
    }
}
