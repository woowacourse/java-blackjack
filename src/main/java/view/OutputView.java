package view;

import model.*;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String BASIC_FORM = "%s: %s";
    private static final Map<String, String> MATCH_FORMAT = Map.of(
            "WIN", "승",
            "DRAW", "무",
            "LOSE", "패"
    );

    public static void printDivisionStart(Dealer dealer, Players values) {
        String dealerNickname = dealer.getNickname();
        List<String> playerNicknames = values.getNicknames();
        System.out.println(formatDivideCommentByNickname(dealerNickname, playerNicknames));
        String dealerCard = dealer.getHands().getFirst().getCard();
        String handsHeader = formatHandsHeader(dealerNickname);
        System.out.println(formatBasicForm(handsHeader, dealerCard));
        List<Player> players = values.getPlayers();
        for (Player player : players) {
            System.out.println(formatHands(player));
        }
        System.out.println();
    }

    public static void printCurrentHands(Player player) {
        System.out.println(formatHands(player));
    }

    public static void printStandingDealer(Dealer dealer) {
        String dealerNickname = dealer.getNickname();
        System.out.println();
        System.out.println(String.format("%s는 %d이하라 한장의 카드를 더 받았습니다.", dealerNickname, 16));
    }


    public static void printAllParticipantScore(Dealer dealer, Players players) {
        printScore(dealer);
        for (Player player : players.getPlayers()) {
            printScore(player);
        }
    }

    private static void printScore(Participant participant) {
        System.out.printf("%s - 결과: %d%n", formatHands(participant), participant.getSum());
    }

    public static void printResult(Dealer dealer, Players players) {
        String dealerNickname = dealer.getNickname();
        String resultFormatByDealer = getResultFormatByDealer(dealer.getMatchResult());

        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf("%s: %s%n", dealerNickname, resultFormatByDealer);

        for (Player player : players.getPlayers()) {
            String nickname = player.getNickname();
            String resultFormatByPlayer = getResultFormatByPlayer(player.getMatchType());
            System.out.printf("%s: %s%n", nickname, resultFormatByPlayer);
        }

    }

    public static String getResultFormatByDealer(Map<MatchType, Integer> matchResult) {
        StringBuilder result = new StringBuilder();
        for (MatchType matchType : matchResult.keySet()) {
            int matchCount = matchResult.get(matchType);
            if (matchCount != 0) {
                result.append(matchCount).append(MATCH_FORMAT.get(matchType.name()));
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static String getResultFormatByPlayer(MatchType matchType) {
        return MATCH_FORMAT.get(matchType.name());
    }

    private static String formatHands(Participant participant) {
        String nickname = participant.getNickname();
        List<String> joinedCards = participant.getHands().stream().map(Card::getCard).toList();
        String joinedHands = String.join(", ", joinedCards);
        String handsHeader = formatHandsHeader(nickname);
        return formatBasicForm(handsHeader, joinedHands);
    }

    private static String formatDivideCommentByNickname(String nickname, List<String> nicknames) {
        return nickname + "와 " + String.join(", ", nicknames) + "에게 2장을 나누었습니다.";
    }

    private static String formatBasicForm(String key, String value) {
        return String.format((BASIC_FORM), key, value);
    }

    private static String formatHandsHeader(String nickname) {
        return String.format("%s카드", nickname);
    }
}
