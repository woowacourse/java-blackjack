package view;

import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final int DEALER_VISIBLE_CARD = 1;

    private OutputView() {
    }

    public static void printStartMessage(List<String> playersName) {
        System.out.println("\n딜러와 " + String.join(", ", playersName) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerCard(Participant dealer) {
        final Card dealerVisibleCard = dealer.getCards().get(DEALER_VISIBLE_CARD);
        System.out.println(dealer.getName() + ": " + dealerVisibleCard.getName());
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
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printPlayerCard(Participant player) {
        System.out.println(makePlayerHandView(player));
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

    public static void printParticipantsResult(Map<String, Integer> result) {
        System.out.println("\n## 최종 수익");

        System.out.println("딜러: " + result.remove("딜러"));
        result.entrySet().stream()
                .map(playerResult -> playerResult.getKey() + ": " + playerResult.getValue())
                .forEach(System.out::println);
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
