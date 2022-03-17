package blackjack.view;

import blackjack.dto.response.CardCountingResult;
import blackjack.dto.response.PlayerCardResult;
import blackjack.dto.response.PlayerGameResult;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";
    private static final String NAME_DELIMITER = ", ";

    public static void printFirstTurnCards(List<PlayerCardResult> firstTurnCards) {
        System.out.println(MessageFormat.format("딜러와 {0}에게 2장의 카드를 나누었습니다.", toCardName(firstTurnCards)));
        firstTurnCards.stream()
            .map(firstTurnCard -> printPlayerCard(firstTurnCard.getPlayerName(), firstTurnCard.getCards()))
            .forEach(System.out::println);
        System.out.println();
    }

    public static void printPlayerCards(PlayerCardResult playerCardResult) {
        System.out.println(printPlayerCard(playerCardResult.getPlayerName(), playerCardResult.getCards()));
        System.out.println();
    }

    public static void printReceivingMoreCardOfDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printCardCountingResult(List<CardCountingResult> cardCountingResults) {
        for (CardCountingResult cardCountingResult : cardCountingResults) {
            System.out.println(MessageFormat.format("{0} - 결과: {1}",
                printPlayerCard(
                    cardCountingResult.getName(), cardCountingResult.getCards()), cardCountingResult.getCount()));
        }
        System.out.println();
    }

    public static void printGameResult(List<PlayerGameResult> playerGameResults) {
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + sumPlayersProfit(playerGameResults) * -1);
        playerGameResults.forEach(result -> System.out.println(result.getPlayerName() + ": " + result.getProfit()));
    }

    private static String toCardName(List<PlayerCardResult> firstTurnCards) {
        return firstTurnCards.stream()
            .map(PlayerCardResult::getPlayerName)
            .filter(name -> !name.equals(DEALER_NAME))
            .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String printPlayerCard(String playerName, List<String> cardNames) {
        return MessageFormat.format("{0}카드: {1}", playerName, String.join(NAME_DELIMITER, cardNames));
    }

    private static int sumPlayersProfit(List<PlayerGameResult> playerGameResults) {
        return playerGameResults.stream()
            .mapToInt(PlayerGameResult::getProfit)
            .sum();
    }
}
