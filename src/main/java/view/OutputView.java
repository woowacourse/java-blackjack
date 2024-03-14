package view;

import dto.CardResponse;
import dto.DealerResponse;
import dto.GameResult;
import dto.ParticipantsResponse;
import dto.PlayerResponse;
import dto.PlayerResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class OutputView {
    private static final String DEALER_DISPLAY_NAME = "딜러";
    public static final String WIN = "승";
    public static final String LOSE = "패";
    public static final String TIE = "무";

    public static void printInitialStatus(final ParticipantsResponse participantsResponse) {
        final DealerResponse dealerResponse = participantsResponse.dealerResponse();
        final List<PlayerResponse> playerResponses = participantsResponse.playerResponse();
        final List<CardResponse> dealerCards = dealerResponse.cardsResponse().stream().limit(1).toList();
        printInitialDrawNotification(playerResponses);

        printStatusWithNewLine(DEALER_DISPLAY_NAME, dealerCards);
        playerResponses.forEach(
                playerResponse -> printStatusWithNewLine(playerResponse.name(), playerResponse.cardResponse()));
        System.out.println();
    }

    public static void printStatusWithNewLine(final String dealerDisplayName, final List<CardResponse> dealerCards) {
        printStatus(dealerDisplayName, dealerCards);
        System.out.println();
    }

    public static void printAfterStatus(final ParticipantsResponse participantsResponse) {
        final DealerResponse dealerResponse = participantsResponse.dealerResponse();
        final List<PlayerResponse> playerResponses = participantsResponse.playerResponse();

        printStatus(DEALER_DISPLAY_NAME, dealerResponse.cardsResponse());
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
        System.out.println(DEALER_DISPLAY_NAME + "와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public static void printStatus(final String name, final List<CardResponse> dealerCards) {
        final StringBuilder stringBuilder = new StringBuilder(name + "카드: ");
        final List<String> cardInfos = new ArrayList<>();
        for (final CardResponse cardResponse : dealerCards) {
            final String denomination = RankMapper.map(cardResponse.rank());
            final String symbol = SuitMapper.map(cardResponse.suit());
            cardInfos.add(denomination + symbol);
        }
        stringBuilder.append(String.join(", ", cardInfos));
        System.out.print(stringBuilder);
    }

    public static void printWinOrLose(final GameResult blackjackResult) {
        System.out.println("## 최종 승패");
        final Set<Entry<String, PlayerResult>> playerEntries = blackjackResult.playerResult().entrySet();
        printDealerWinOrLose(blackjackResult);
        printPlayerWinOrLose(playerEntries);
        System.out.println();
    }

    private static void printDealerWinOrLose(final GameResult blackjackResult) {
        System.out.print(DEALER_DISPLAY_NAME + ": ");
        final List<Integer> printOrder = List.of(blackjackResult.dealerWin(), blackjackResult.dealerLose(),
                blackjackResult.dealerTie());
        final List<String> suffix = List.of(WIN, LOSE, TIE);

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
        System.out.println(DEALER_DISPLAY_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    private static String determineWinOrLose(final PlayerResult playerResult) {
        if (playerResult == PlayerResult.WIN) {
            return WIN;
        }
        if (playerResult == PlayerResult.LOSE) {
            return LOSE;
        }
        return TIE;
    }
}
