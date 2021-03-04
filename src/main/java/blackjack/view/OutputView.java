package blackjack.view;

import blackjack.domain.carddeck.Card;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String INIT_GAME_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String HAND_FORMAT = " 카드: ";
    private static final String SCORE_FORMAT = " - 결과: ";

    public static void printInitGame(final List<Player> players) {
        System.out.printf(INIT_GAME_FORMAT, joinPlayerNames(players));
        System.out.print(NEW_LINE);
    }

    private static String joinPlayerNames(final List<Player> players) {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(DELIMITER));
    }

    public static void printCards(final List<Player> players) {
        StringBuilder sb = new StringBuilder();
        players.forEach(player ->
            sb.append(player.getName())
                .append(HAND_FORMAT)
                .append(joinCards(player.getHand()))
                .append(NEW_LINE)
        );
        System.out.print(sb);
    }

    private static String joinCards(final List<Card> cards) {
        List<String> cardStrings = new ArrayList<>();
        for (Card card : cards) {
            cardStrings.add(card.getNumberName() + card.getPatternName());
        }
        return String.join(DELIMITER, cardStrings);
    }
}
