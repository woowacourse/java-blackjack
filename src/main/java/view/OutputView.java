package view;

import domain.card.Card;
import domain.gameresult.GameResult;
import domain.player.PlayerReadOnly;
import domain.player.PlayersReadOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DEALER_CARD_CONDITION_FORMAT = "%s: %s";
    private static final String PARTICIPANT_CARD_CONDITION_FORMAT = "%s카드: %s";

    public static void printParticipantNamesGuide() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printPlayersInformation(PlayersReadOnly players) {
        List<String> playerNames = players.getAllNames();
        printPlayerNames(playerNames);
        printPlayerCardConditions(players);
    }

    private static void printPlayerNames(List<String> playerNames) {
        playerNames = new ArrayList<>(playerNames);
        printEmptyLine();
        println(String.format("%s와 %s에게 2장을 나누어주었습니다.",
                playerNames.remove(0),
                String.join(", ", playerNames)));
    }

    private static void printPlayerCardConditions(PlayersReadOnly players) {
        printDealerCardCondition(players.getDealer());
        printParticipantCardCondition(players.getGamblers());
    }

    private static void printDealerCardCondition(PlayerReadOnly dealer) {
        Card card = dealer.getFirstCard();
        printPlayerCardCondition(dealer, DEALER_CARD_CONDITION_FORMAT, parseCardInformation(card));
    }

    public static void printParticipantCardCondition(List<PlayerReadOnly> gamblers) {
        for (PlayerReadOnly gambler : gamblers) {
            printPlayerCardCondition(gambler, PARTICIPANT_CARD_CONDITION_FORMAT, parseCardsInformation(gambler.getCards()));
        }
    }

    private static String parseCardsInformation(List<Card> cards) {
        return cards.stream()
                .map(OutputView::parseCardInformation)
                .collect(Collectors.joining(", "));
    }

    private static String parseCardInformation(Card card) {
        String numberDescription = card.getNumberDescription();
        String shapeDescription = card.getShapeDescription();
        return numberDescription.concat(shapeDescription);
    }

    private static void printPlayerCardCondition(PlayerReadOnly player, String format, String cardsDisplay) {
        println(String.format(format, player.getName(), cardsDisplay));
    }

    public static void printAddCardGuide(String name) {
        printEmptyLine();
        println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
    }

    public static void printBurstMessage(String name) {
        println(String.format("%s님은 버스트 되셨습니다. 더이상 카드를 뽑을 수 없습니다.", name));
    }

    public static void printGiveDealerCardMessage() {
        printEmptyLine();
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayersFinalInformation(PlayersReadOnly players) {
        printEmptyLine();
        for (PlayerReadOnly player : players.getAllPlayers()) {
            printPlayerCardCondition(player, "%s카드: %s", parseCardsInformation(player.getCards()));
            println(String.format(" - 결과: %d", player.getTotalScore()));
        }
    }

    public static void printPlayersGameResults(GameResult gameResult) {
        printEmptyLine();
        println("## 최종 승패");
        gameResult.doLogicWithNameAndBetValue((name, result) ->
                println(String.format("%s: %d", name, result)));
    }

    public static void println(String message) {
        System.out.println(message);
    }

    private static void printEmptyLine() {
        println("");
    }

    public static void printBetNameGuide(String name) {
        printEmptyLine();
        println(String.format("%s의 배팅 금액은?", name));
    }
}
