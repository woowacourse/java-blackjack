package view;

import model.VictoryResultDto;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import model.participant.*;
import model.score.MatchResult;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String BASIC_FORM = "%s: %s";
    private static final Map<String, String> MATCH_FORMAT = Map.of(
            "WIN", "승",
            "DRAW", "무",
            "LOSE", "패"
    );

    private static final Map<String, String> RANK_FORMAT = Map.ofEntries(
            Map.entry("TWO", "2"),
            Map.entry("THREE", "3"),
            Map.entry("FOUR", "4"),
            Map.entry("FIVE", "5"),
            Map.entry("SIX", "6"),
            Map.entry("SEVEN", "7"),
            Map.entry("EIGHT", "8"),
            Map.entry("NINE", "9"),
            Map.entry("TEN", "10"),
            Map.entry("KING", "K"),
            Map.entry("JACK", "J"),
            Map.entry("QUEEN", "Q"),
            Map.entry("ACE", "A")
    );

    private static final Map<String, String> SUIT_FORMAT = Map.of(
            "DIAMONDS", "다이아몬드",
            "HEARTS", "하트",
            "SPADES", "스페이드",
            "CLUBS", "클로버"
    );

    public static void printDivisionStart(Dealer dealer, Players values) {
        String dealerNickname = dealer.getNickname();
        List<String> playerNicknames = values.getNicknames();
        System.out.println(formatDistributeCommentByNickname(dealerNickname, playerNicknames));
        Card card = dealer.getHands().getFirst();
        String formattedDealerCard = getCardFormat(card);
        String handsHeader = formatHandsHeader(dealerNickname);
        System.out.println(formatBasicForm(handsHeader, formattedDealerCard));
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
        System.out.printf("%s - 결과: %d%n", formatHands(participant), participant.getScore());
    }

    public static void printResult(VictoryResultDto dto) {
        String dealerNickname = "딜러"; // TODO: 딜러 닉네임 어떻게 가져율 것인지
        String resultFormatByDealer = getResultFormatByDealer(dto.dealerMatchResult());

        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf("%s: %s%n", dealerNickname, resultFormatByDealer);

        for (Player player : dto.playersMatchResult().keySet()) {
            String nickname = player.getNickname();
            String resultFormatByPlayer = getResultFormatByPlayer(dto.playersMatchResult().get(player));
            System.out.printf("%s: %s%n", nickname, resultFormatByPlayer);
        }

    }

    public static String getResultFormatByDealer(Map<MatchResult, Integer> matchResult) {
        StringBuilder result = new StringBuilder();
        for (MatchResult matchType : matchResult.keySet()) {
            int matchCount = matchResult.get(matchType);
            if (matchCount != 0) {
                result.append(matchCount).append(MATCH_FORMAT.get(matchType.name()));
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static String getResultFormatByPlayer(MatchResult matchType) {
        return MATCH_FORMAT.get(matchType.name());
    }

    public static String getCardFormat(Card card) {
        Rank rankType = card.getRank();
        Suit suitType = card.getSuit();
        return RANK_FORMAT.get(rankType.name()) + SUIT_FORMAT.get(suitType.name());
    }

    private static String formatHands(Participant participant) {
        String nickname = participant.getNickname();
        List<String> joinedCards = participant.getHands().stream().map(
                OutputView::getCardFormat
        ).toList();
        String joinedHands = String.join(", ", joinedCards);
        String handsHeader = formatHandsHeader(nickname);
        return formatBasicForm(handsHeader, joinedHands);
    }

    private static String formatDistributeCommentByNickname(String nickname, List<String> nicknames) {
        return nickname + "와 " + String.join(", ", nicknames) + "에게 2장을 나누었습니다.";
    }

    private static String formatBasicForm(String key, String value) {
        return String.format((BASIC_FORM), key, value);
    }

    private static String formatHandsHeader(String nickname) {
        return String.format("%s카드", nickname);
    }
}
