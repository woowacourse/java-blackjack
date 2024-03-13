package view;

import dto.CardResponse;
import dto.DealerResponse;
import dto.GameResult;
import dto.ParticipantsResponse;
import dto.PlayerResponse;
import dto.PlayerResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OutputView {
    private static final String DEALER_NAME = "딜러";

    public static void printInitialStatus(final ParticipantsResponse participantsResponse) {
        final DealerResponse dealerResponse = participantsResponse.dealerResponse();
        final List<PlayerResponse> playerResponses = participantsResponse.playerResponse();
        final List<CardResponse> dealerCards = dealerResponse.cardsResponse().stream().limit(1).toList();
        printInitialDrawNotification(playerResponses);

        printStatus(DEALER_NAME, dealerCards);
        System.out.println();
        playerResponses.forEach(playerResponse -> {
            printStatus(playerResponse.name(), playerResponse.cardResponse());
            System.out.println();
        });

        System.out.println();
    }

    public static void printAfterStatus(final ParticipantsResponse participantsResponse) {
        final DealerResponse dealerResponse = participantsResponse.dealerResponse();
        final List<PlayerResponse> playerResponses = participantsResponse.playerResponse();

        printStatus(DEALER_NAME, dealerResponse.cardsResponse());
        printResult(dealerResponse.score());
        playerResponses.forEach(playerResponse -> {
            printStatus(playerResponse.name(), playerResponse.cardResponse());
            printResult(playerResponse.score());
        });
        System.out.println();
    }

    private static void printResult(final Integer score) {
        System.out.printf(" - 결과: %d", score);
        System.out.println();
    }

    private static void printInitialDrawNotification(final List<PlayerResponse> playerResponses) {
        final List<String> names = playerResponses.stream().map(PlayerResponse::name).toList();
        System.out.println(DEALER_NAME + "와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public static void printStatus(final String name, final List<CardResponse> dealerCards) {
        final StringBuilder stringBuilder = new StringBuilder(name + "카드: ");
        final List<String> cardInfos = new ArrayList<>();
        for (final CardResponse cardResponse : dealerCards) {
            final String denomination = rankToMessage(cardResponse.rank());
            final String symbol = suitToMessage(cardResponse.suit());
            cardInfos.add(denomination + symbol);
        }
        stringBuilder.append(String.join(", ", cardInfos));
        System.out.print(stringBuilder);
    }

    public static void printWinOrLose(final GameResult blackjackResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        final Set<Entry<String, PlayerResult>> playerEntries = blackjackResult.playerResult().entrySet();
        printDealerWinOrLose(blackjackResult);
        printPlayerWinOrLose(playerEntries);
    }
    //

    private static void printDealerWinOrLose(final GameResult blackjackResult) {
        System.out.print(DEALER_NAME + ": ");
        final List<Integer> printOrder = List.of(blackjackResult.dealerWin(), blackjackResult.dealerLose(),
                blackjackResult.dealerTie());
        final List<String> suffix = List.of("승", "패", "무");

        for (int order = 0; order < printOrder.size(); order++) {
            printResultWithCounter(printOrder.get(order), suffix.get(order));
        }
        System.out.println();
    }

    private static void printResultWithCounter(final int resultCount, final String suffix) {
        if (resultCount <= 0) {
            return;
        }
        System.out.print(resultCount + " " + suffix + " ");
    }

    private static void printPlayerWinOrLose(final Set<Entry<String, PlayerResult>> playerEntries) {
        for (final var entry : playerEntries) {
            final String name = entry.getKey();
            final PlayerResult playerResult = entry.getValue();
            printPlayerResult(name, playerResult);
        }
    }


    private static void printPlayerResult(final String name, final PlayerResult playerResult) {
        System.out.println(name + ": " + determineWinOrLose(playerResult));
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.println(DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private static String determineWinOrLose(final PlayerResult playerResult) {
        if (playerResult == PlayerResult.WIN) {
            return "승";
        }
        if (playerResult == PlayerResult.LOSE) {
            return "패";
        }
        return "무";
    }

    private static String rankToMessage(final String rank) {
        final Map<String, String> mapper = new HashMap<>();
        mapper.put("ACE", "A");
        mapper.put("ONE", "1");
        mapper.put("TWO", "2");
        mapper.put("THREE", "3");
        mapper.put("FOUR", "4");
        mapper.put("FIVE", "5");
        mapper.put("SIX", "6");
        mapper.put("SEVEN", "7");
        mapper.put("EIGHT", "8");
        mapper.put("NINE", "9");
        mapper.put("TEN", "10");
        mapper.put("JACK", "J");
        mapper.put("QUEEN", "Q");
        mapper.put("KING", "K");
        return mapper.get(rank);
    }

    private static String suitToMessage(final String suit) {
        final Map<String, String> mapper = new HashMap<>();
        mapper.put("HEARTS", "하트");
        mapper.put("CLUBS", "클로버");
        mapper.put("SPADES", "스페이드");
        mapper.put("DIAMONDS", "다이아몬드");
        return mapper.get(suit);
    }
}
