package view;

import dto.CardResponse;
import dto.DealerResponse;
import dto.GameResult;
import dto.ParticipantsResponse;
import dto.PlayerResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class OutputView {
    private static final String DEALER_DISPLAY_NAME = "딜러";

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

    public static void printProfit(final GameResult blackjackResult) {
        System.out.println("## 최종 수익");

        final Double dealerResult = blackjackResult.dealerResult();
        final Set<Entry<String, Double>> playerEntries = blackjackResult.playerResult().entrySet();
        System.out.printf("딜러: %.1f%n", dealerResult);
        playerEntries.forEach(r -> System.out.printf("%s: %.1f%n", r.getKey(), r.getValue()));
    }

    public static void printDealerHitMessage() {
        System.out.println(DEALER_DISPLAY_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }
}
