package view;

import domain.card.Card;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final String DEALER_CARD_CONDITION_FORMAT = "%s: %s%n";
    public static final String PARTICIPANT_CARD_CONDITION_FORMAT = "%s카드: %s%n";

    public static void printParticipantNamesGuide() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void println(String message) {
        System.out.println(message);
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
        System.out.printf("%s와 %s에게 2장을 나누어주었습니다.%n",
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

    private static void printParticipantCardCondition(List<Player> participants) {
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
}
