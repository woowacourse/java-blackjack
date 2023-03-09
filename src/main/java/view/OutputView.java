package view;

import domain.PlayerGameResult;
import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_WIN = "승";
    private static final String DEALER_DRAW = "무";
    private static final String DEALER_LOSE = "패";
    private static final int DEALER_VISIBLE_CARD = 1;

    private OutputView() {
    }

    public static void printStartMessage(List<String> playersName) {
        System.out.println("\n딜러와 " + String.join(", ", playersName) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerCard(Participant dealer) {
        System.out.println(dealer.getName() + ": " + makeCardView(dealer.getCards().get(DEALER_VISIBLE_CARD)));
    }

    public static void printPlayersCard(List<Player> players) {
        players.stream()
                .map(OutputView::makePlayerHandView)
                .forEach(System.out::println);
        System.out.println();
    }

    private static String makePlayerHandView(Participant player) {
        return player.getName() + "카드: " + makeCardsView(player.getCards());
    }

    private static String makeCardsView(List<Card> cards) {
        return cards.stream()
                .map(OutputView::makeCardView)
                .collect(Collectors.joining(", "));
    }

    public static void printPlayerCard(Participant player) {
        System.out.println(makePlayerHandView(player));
    }

    private static String makeCardView(Card card) {
        return card.getRank().getName() + card.getSuit().getName();
    }

    public static void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllHands(Participant dealer, List<Player> players) {
        String dealerCards = makeCardsView(dealer.getCards());
        System.out.println("\n" + dealer.getName() + " 카드: " + dealerCards + " - 결과: " + dealer.calculateScore());

        players.stream()
                .map(player -> makePlayerHandView(player) + " - 결과: " + player.calculateScore())
                .forEach(System.out::println);
    }

    public static void printParticipantsResult(Map<String, PlayerGameResult> playersResult) {
        System.out.println("\n## 최종 승패");
        printDealerResult(playersResult);
        printPlayersResult(playersResult);
    }

    private static void printDealerResult(Map<String, PlayerGameResult> playersResult) {
        System.out.print("딜러: ");

        Map<PlayerGameResult, Long> dealerResult = playersResult.values().stream()
                .collect(Collectors.groupingBy(result -> result, Collectors.counting()));

        Arrays.stream(PlayerGameResult.values())
                .filter(dealerResult::containsKey)
                .map(playerResult -> dealerResult.get(playerResult) + makeDealerResultView(playerResult))
                .forEach(System.out::println);
    }

    private static String makeDealerResultView(PlayerGameResult playerGameResult) {
        if (PlayerGameResult.WIN.equals(playerGameResult)) {
            return DEALER_LOSE + " ";
        }

        if (PlayerGameResult.DRAW.equals(playerGameResult)) {
            return DEALER_DRAW + " ";
        }

        return DEALER_WIN + " ";
    }

    private static void printPlayersResult(Map<String, PlayerGameResult> playersResult) {
        System.out.println();
        playersResult.entrySet().stream()
                .map(player -> player.getKey() + ": " + player.getValue().getName())
                .forEach(System.out::println);
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
