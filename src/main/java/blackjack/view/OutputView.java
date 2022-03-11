package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.GameResponse;
import blackjack.domain.Match;
import blackjack.domain.MatchResult;
import blackjack.domain.Player;
import blackjack.domain.Results;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void announceStartGame(List<String> playerNames) {
        System.out.println(String.join(", ", playerNames) + "에게 2장의 카드를 각각 나누었습니다.");
    }

    public static void announcePresentCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            printFirstStartCards(gameResponse);
        }
    }

    private static void printFirstStartCards(GameResponse gameResponse) {
        String playerName = gameResponse.getName();
        if (playerName.equals("딜러")) {
            String cardOutputFormat = hideOneCard(gameResponse);
            System.out.printf("%s 카드: %s\n", playerName, cardOutputFormat);
            return;
        }
        String cardOutputFormat = toCardOutputFormat(gameResponse);
        System.out.printf("%s 카드: %s\n", playerName, cardOutputFormat);
    }

    private static String hideOneCard(GameResponse gameResponse) {
        return gameResponse.getDeck()
                .getCards()
                .stream()
                .findFirst()
                .orElseThrow()
                .toString();
    }

    public static void announceDealerGetMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void announceDealerStopMoreCard() {
        System.out.println("딜러는 17이상이라 카드를 더 받지 않습니다.");
    }

    public static void announceResultCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            String playerName = gameResponse.getName();
            String cardOutputFormat = toCardOutputFormat(gameResponse);
            int totalPoint = gameResponse.getDeck().sumPoints();

            System.out.printf("%s 카드: %s - 결과: %d\n", playerName, cardOutputFormat, totalPoint);
        }
    }

    private static String toCardOutputFormat(GameResponse gameResponse) {
        return gameResponse.getDeck()
                .getCards()
                .stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public static void announceResultWinner(Results results) {
        for (Player player : results.getPlayers()) {
            MatchResult result = results.getResult(player);
            String matchResult = toMatchResultFormat(result.getMatch());
            System.out.println(player.getName() + ": " + matchResult);
        }
    }

    private static String toMatchResultFormat(Map<Match, Integer> matchResult) {
        StringBuilder sb = new StringBuilder();
        for (Match match : matchResult.keySet()) {
            sb.append(matchResult.get(match)).append(match.getResult());
        }
        return sb.toString();
    }
}
