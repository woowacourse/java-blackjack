package view;

import domain.card.Card;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public static final String DEALER_CARD_CONDITION_FORMAT = "%s: %s%n";
    public static final String PARTICIPANT_CARD_CONDITION_FORMAT = "%s카드: %s%n";
    
    private OutputView() {
        throw new IllegalArgumentException("인스턴스를 생성할 수 없는 클래스입니다.");
    }
    
    public static void printParticipantNamesGuide() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printPlayersInformation(List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
        printPlayerNames(playerNames);
        printPlayerCardConditions(players);
    }

    private static void printPlayerNames(List<String> playerNames) {
        playerNames = new ArrayList<>(playerNames);
        System.out.printf("%n%s와 %s에게 2장을 나누어주었습니다.%n",
                playerNames.remove(0),
                String.join(", ", playerNames));
    }

    private static void printPlayerCardConditions(List<Player> players) {
        players = new ArrayList<>(players);
        Player dealer = players.remove(0);
        printDealerCardCondition(dealer);
        printParticipantCardCondition(players);
    }

    private static void printDealerCardCondition(Player dealer) {
        Card card = dealer.getCards().get(0);
        printPlayerCardCondition(dealer, DEALER_CARD_CONDITION_FORMAT, parseCardInformation(card));
    }

    public static void printParticipantCardCondition(List<Player> participants) {
        for (Player participant : participants) {
            printPlayerCardCondition(participant, PARTICIPANT_CARD_CONDITION_FORMAT, parseCardsInformation(participant.getCards()));
        }
    }

    private static String parseCardsInformation(List<Card> cards) {
        return cards.stream()
                .map(OutputView::parseCardInformation)
                .collect(Collectors.joining(", "));
    }

    private static String parseCardInformation(Card card) {
        String numberDescription = NumberDisplayMatcher.displayMessage(card.getNumber());
        String shapeDescription = ShapeDisplayMatcher.displayName(card.getShape());
        return numberDescription.concat(shapeDescription);
    }

    private static void printPlayerCardCondition(Player player, String format, String cardsDisplay) {
        System.out.printf(format, player.getName(), cardsDisplay);
    }

    public static void printAddCardGuide(String name) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
    }

    public static void printBurstMessage(String name) {
        System.out.printf("%s님은 버스트 되셨습니다. 더이상 카드를 뽑을 수 없습니다.%n", name);
    }

    public static void printGiveDealerCardMessage() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayersFinalInformation(List<Player> players) {
        println("");
        for (Player player : players) {
            printPlayerCardCondition(player, "%s카드: %s", parseCardsInformation(player.getCards()));
            System.out.printf(" - 결과: %d%n", player.getTotalScore());
        }
    }

    public static void printPlayersGameResults(List<Player> players) {
        players = new ArrayList<>(players);
        println("\n## 최종 승패");
        printDealerGameResult(players.remove(0));
        printParticipantsGameResult(players);
    }

    private static void printDealerGameResult(Player dealer) {
        System.out.printf("딜러: %s%n", parsePlayerGameResultDisplay(dealer));
    }

    private static String parsePlayerGameResultDisplay(Player dealer) {
        List<String> strings = List.of("승", "패", "무");
        List<Integer> gameResults = dealer.getGameResult();

        return IntStream.range(0, gameResults.size())
                .filter(dealerGameResultIndex -> !OutputView.isGameResultCountZero(gameResults, dealerGameResultIndex))
                .mapToObj(dealerGameResultIndex -> gameResults.get(dealerGameResultIndex) + strings.get(dealerGameResultIndex))
                .collect(Collectors.joining(" "));

    }

    private static boolean isGameResultCountZero(List<Integer> gameResults, int dealerGameResultIndex) {
        return gameResults.get(dealerGameResultIndex) == 0;
    }

    private static void printParticipantsGameResult(List<Player> participants) {
        participants.stream()
                .map(OutputView::parseParticipantGameResultDisplay)
                .forEach(OutputView::println);
    }

    private static String parseParticipantGameResultDisplay(Player player) {
        return String.format("%s: %s", player.getName(), parsePlayerGameResultDisplay(player));
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
