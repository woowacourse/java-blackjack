package view;

import card.Card;
import card.Suit;
import card.Rank;
import result.MatchResultType;
import participant.Dealer;
import participant.Player;
import participant.Players;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String BASIC_FORM = "%s: %s";

    private static final String DEALER_NICKNAME = "딜러";

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
            Map.entry("SOFT_ACE", "A"),
            Map.entry("HARD_ACE", "A")
    );

    private static final Map<String, String> SUIT_FORMAT = Map.of(
            "DIAMONDS", "다이아몬드",
            "HEARTS", "하트",
            "SPADES", "스페이드",
            "CLUBS", "클로버"
    );

    public static void printDivisionStart(Dealer dealer, List<Player> players) {
        List<String> playerNicknames = players.stream()
                .map(Player::getNickname)
                .toList();

        System.out.println(formatDivideCommentByNickname(DEALER_NICKNAME, playerNicknames));

        Card card = dealer.getCards().getFirst();
        String formattedDealerCard = getCardFormat(card);
        String handsHeader = formatHandsHeader(DEALER_NICKNAME);
        System.out.println(formatBasicForm(handsHeader, formattedDealerCard));

        for (Player player : players) {
            System.out.println(formatHandsOfPlayer(player));
        }
        System.out.println();
    }

    public static void printCurrentHands(Player player) {
        System.out.println(formatHandsOfPlayer(player));
    }

    public static void printHittingDealer(Dealer dealer) {
        System.out.println();
        System.out.println(String.format("%s는 %d이하라 한장의 카드를 더 받았습니다.", DEALER_NICKNAME, 16));
    }


    public static void printAllParticipantScore(Dealer dealer, Players players) {
        printScore(formHandsOfDealer(dealer), dealer.getScore().getValue());
        for (Player player : players.getPlayers()) {
            printScore(formatHandsOfPlayer(player), player.getScore().getValue());
        }
    }

    private static void printScore(String hands, int score) {
        System.out.printf("%s - 결과: %d%n", hands, score);
    }

    public static void printResult(
            Dealer dealer,
            Map<MatchResultType, Long> dealerMatchResult,
            Map<Player, MatchResultType> playerMatchResult
    ) {
        String resultFormatByDealer = getDealerResultFormat(dealerMatchResult);

        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf("%s: %s%n", DEALER_NICKNAME, resultFormatByDealer);

        for (Map.Entry<Player, MatchResultType> entry : playerMatchResult.entrySet()) {
            String nickname = entry.getKey().getNickname();
            String resultFormatByPlayer = getResultFormatByPlayer(entry.getValue());
            System.out.printf("%s: %s%n", nickname, resultFormatByPlayer);
        }

    }

    public static String getDealerResultFormat(Map<MatchResultType, Long> matchResult) {
        StringBuilder result = new StringBuilder();
        for (MatchResultType matchResultType : matchResult.keySet()) {
            long matchCount = matchResult.get(matchResultType);
            if (matchCount != 0) {
                result.append(matchCount).append(MATCH_FORMAT.get(matchResultType.name()));
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static String getResultFormatByPlayer(MatchResultType matchResultType) {
        return MATCH_FORMAT.get(matchResultType.name());
    }

    public static String getCardFormat(Card card) {
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        return RANK_FORMAT.get(rank.toString()) + SUIT_FORMAT.get(suit.name());
    }

    private static String formHandsOfDealer(Dealer dealer) {
        String handsHeader = formatHandsHeader("딜러");
        String dealerCards = formatHands(dealer.getCards());
        return formatBasicForm(handsHeader, dealerCards);

    }

    private static String formatHandsOfPlayer(Player player) {
        String nickname = player.getNickname();
        String handsHeader = formatHandsHeader(nickname);
        String playerCards = formatHands(player.getCards());
        return formatBasicForm(handsHeader, playerCards);
    }

    private static String formatHands(List<Card> cards) {
        List<String> joinedCards = cards.stream().map(
                OutputView::getCardFormat
        ).toList();
        return String.join(", ", joinedCards);
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
