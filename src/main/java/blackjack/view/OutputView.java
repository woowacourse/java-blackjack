package blackjack.view;

import blackjack.domain.machine.GameResponse;
import blackjack.domain.machine.Match;
import blackjack.domain.machine.MatchResult;
import blackjack.domain.machine.Results;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String COMMA_DELIMITER = ", ";
    private static final String DISTRIBUTE_TWO_CARDS_MESSAGE = "에게 2장의 카드를 각각 나누었습니다.";
    private static final String CARD_OUTPUT_FORMAT = "%s 카드: %s";
    private static final String NEW_LINE = "\n";
    private static final String TOTAL_POINT_OUTPUT_FORMAT = " - 결과: %d";
    private static final String DEALER_GIVEN_ONE_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_GIVEN_NO_MORE_CARD_MESSAGE = "딜러는 17이상이라 카드를 더 받지 않습니다.";
    private static final String MATCH_RESULT_DELIMITER = ": ";
    private static final String NO_CARDS_ERROR_MESSAGE = "카드가 존재하지 않습니다.";

    public static void announceStartGame(List<String> playerNames) {
        System.out.println(String.join(COMMA_DELIMITER, playerNames) + DISTRIBUTE_TWO_CARDS_MESSAGE);
    }

    public static void announcePresentCards(GameResponse gameResponse) {
            printFirstStartCards(gameResponse);
    }

    public static void announcePresentCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            printFirstStartCards(gameResponse);
        }
    }

    public static void announceDealerGetMoreCard() {
        System.out.println(DEALER_GIVEN_ONE_MORE_CARD_MESSAGE);
    }

    public static void announceDealerStopMoreCard() {
        System.out.println(DEALER_GIVEN_NO_MORE_CARD_MESSAGE);
    }

    public static void announceResultCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            String playerName = gameResponse.getName();
            String cardOutputFormat = toCardOutputFormat(gameResponse);
            int totalPoint = gameResponse.getTotalPoint();
            System.out.printf(CARD_OUTPUT_FORMAT + TOTAL_POINT_OUTPUT_FORMAT + NEW_LINE, playerName, cardOutputFormat,
                    totalPoint);
        }
    }

    public static void announceResultWinner(Results results) {
        for (Player player : results.getPlayers()) {
            MatchResult result = results.getResult(player);
            String matchResult = toMatchResultFormat(result.getMatch());
            System.out.println(player.getName() + MATCH_RESULT_DELIMITER + matchResult);
        }
    }

    private static String toCardOutputFormat(GameResponse gameResponse) {
        return String.join(COMMA_DELIMITER, gameResponse.getDeck());
    }

    private static String toMatchResultFormat(Map<Match, Integer> matchResult) {
        StringBuilder resultFormat = new StringBuilder();
        for (Match match : matchResult.keySet()) {
            resultFormat.append(matchResult.get(match)).append(match.getResult());
        }
        return resultFormat.toString();
    }


    private static void printFirstStartCards(GameResponse gameResponse) {
        String playerName = gameResponse.getName();
        if (playerName.equals(Dealer.NAME)) {
            String cardOutputFormat = hideOneCard(gameResponse);
            System.out.printf(CARD_OUTPUT_FORMAT + NEW_LINE, playerName, cardOutputFormat);
            return;
        }
        String cardOutputFormat = toCardOutputFormat(gameResponse);
        System.out.printf(CARD_OUTPUT_FORMAT + NEW_LINE, playerName, cardOutputFormat);
    }

    private static String hideOneCard(GameResponse gameResponse) {
        return gameResponse.getDeck().get(0);
    }
}
