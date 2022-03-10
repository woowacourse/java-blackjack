package blackjack;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NAME_DELIMITER = ", ";

    public static void printPlayersDefaultCard(List<Participant> participants) {
        System.out.println(MessageFormat.format("딜러와 {0}에게 2장의 카드를 나누었습니다.", concatPlayerName(participants)));
        for (Participant participant : participants) {
            System.out.println(MessageFormat.format("{0}카드: {1}", participant.getName(), participant.openCard().stream()
                    .map(OutputView::toCardName)
                    .collect(Collectors.joining(NAME_DELIMITER))));
        }
    }

    public static void printPlayerCards(Participant participant) {
        System.out.println(getPlayerCard(participant));
    }

    public static void printReceivingMoreCardOfDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(Map<Participant, Integer> cardResult) {
        cardResult.forEach((player, count) -> System.out.println(MessageFormat.format("{0} - 결과: {1}", getPlayerCard(player), count)));
    }

    public static void printGameResult(Map<Outcome, List<Player>> gameResult) {
        System.out.println("## 최종 승패");
        gameResult.forEach((outcome, players) ->
                players.forEach(player -> System.out.println(player.getName() + ": " + outcome.getValue())));
    }

    private static String getPlayerCard(Participant participant) {
        return MessageFormat.format("{0}카드: {1}", participant.getName(), concatCardName(participant.getHoldCards()));
    }

    private static String concatPlayerName(List<Participant> players) {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String concatCardName(HoldCards holdCards) {
        return holdCards.getCards()
                .stream()
                .map(OutputView::toCardName)
                .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String toCardName(Card card) {
        return card.getNumber().getName() + card.getSuit().getName();
    }
}
