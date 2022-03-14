package blackjack.view;

import blackjack.domain.PlayerOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.dto.FirstTurnCards;
import blackjack.dto.PlayerCardResult;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";
    private static final String NAME_DELIMITER = ", ";

    public static void printFirstTurnCards(List<FirstTurnCards> firstTurnCards) {
        System.out.println(MessageFormat.format("딜러와 {0}에게 2장의 카드를 나누었습니다.", toName(firstTurnCards)));
        firstTurnCards.stream()
            .map(firstTurnCard -> printPlayerCard(firstTurnCard.getPlayerName(), firstTurnCard.getCards()))
            .forEach(System.out::println);
        System.out.println();
    }

    public static void printPlayerCards(PlayerCardResult playerCardResult) {
        System.out.println(printPlayerCard(playerCardResult.getPlayerName(), playerCardResult.getCards()));
    }

    public static void printReceivingMoreCardOfDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printCardResult(Map<Participant, Integer> cardResult) {
        cardResult.forEach(
            (player, count) -> System.out.println(MessageFormat.format("{0} - 결과: {1}", getPlayerCard(player), count)));
        System.out.println();
    }

    public static void printGameResult(Map<PlayerOutcome, List<Player>> gameResult) {
        System.out.println("## 최종 승패");

        System.out.println("딜러: "
            + gameResult.getOrDefault(PlayerOutcome.LOSE, Collections.emptyList()).size() + "승 "
            + gameResult.getOrDefault(PlayerOutcome.WIN, Collections.emptyList()).size() + "패 "
            + gameResult.getOrDefault(PlayerOutcome.DRAW, Collections.emptyList()).size() + "무");

        gameResult.forEach((outcome, players) ->
            players.forEach(player -> System.out.println(player.getName() + ": " + outcome.getValue())));
    }

    private static String toName(List<FirstTurnCards> firstTurnCards) {
        return firstTurnCards.stream()
            .map(FirstTurnCards::getPlayerName)
            .filter(name -> !name.equals(DEALER_NAME))
            .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String printPlayerCard(String playerName, List<String> cardNames) {
        return MessageFormat.format("{0}카드: {1}", playerName, String.join(NAME_DELIMITER, cardNames));
    }

    private static String getPlayerCard(Participant participant) {
        return MessageFormat.format("{0}카드: {1}", participant.getName(), concatCardName(participant.getHoldCards()));
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
