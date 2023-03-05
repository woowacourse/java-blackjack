package view;

import domain.card.Card;
import domain.gameresult.GameResultReadOnly;
import domain.gameresult.Result;
import domain.player.PlayerReadOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DEALER_CARD_CONDITION_FORMAT = "%s: %s%n";
    private static final String PARTICIPANT_CARD_CONDITION_FORMAT = "%s카드: %s%n";

    public static void printParticipantNamesGuide() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printPlayersInformation(List<PlayerReadOnly> players) {
        List<String> playerNames = players.stream()
                .map(PlayerReadOnly::getName)
                .collect(Collectors.toUnmodifiableList());
        printPlayerNames(playerNames);
        printPlayerCardConditions(players);
    }

    private static void printPlayerNames(List<String> playerNames) {
        playerNames = new ArrayList<>(playerNames);
        printEmptyLine();
        System.out.printf("%s와 %s에게 2장을 나누어주었습니다.%n",
                playerNames.remove(0),
                String.join(", ", playerNames));
    }

    private static void printPlayerCardConditions(List<PlayerReadOnly> players) {
        players = new ArrayList<>(players);
        PlayerReadOnly dealer = players.remove(0);
        printDealerCardCondition(dealer);
        printParticipantCardCondition(players);
    }

    private static void printDealerCardCondition(PlayerReadOnly dealer) {
        Card card = dealer.getCards().get(0);
        printPlayerCardCondition(dealer, DEALER_CARD_CONDITION_FORMAT, parseCardInformation(card));
    }

    public static void printParticipantCardCondition(List<PlayerReadOnly> participants) {
        for (PlayerReadOnly participant : participants) {
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

    private static void printPlayerCardCondition(PlayerReadOnly player, String format, String cardsDisplay) {
        System.out.printf(format, player.getName(), cardsDisplay);
    }

    public static void printAddCardGuide(String name) {
        printEmptyLine();
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
    }

    public static void printBurstMessage(String name) {
        System.out.printf("%s님은 버스트 되셨습니다. 더이상 카드를 뽑을 수 없습니다.%n", name);
    }

    public static void printGiveDealerCardMessage() {
        printEmptyLine();
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayersFinalInformation(List<PlayerReadOnly> players) {
        printEmptyLine();

        for (PlayerReadOnly player : players) {
            printPlayerCardCondition(player, "%s카드: %s", parseCardsInformation(player.getCards()));
            System.out.printf(" - 결과: %d%n", player.getTotalScore());
        }
    }

    public static void printPlayersGameResults(GameResultReadOnly gameResult) {
        printEmptyLine();
        println("## 최종 승패");
        printDealerGameResult(gameResult.getDealerResult());
        printParticipantsGameResult(gameResult.getParticipantsResult());
    }

    private static void printDealerGameResult(Map<Result, Integer> gameResult) {
        System.out.printf("딜러: %s%n", parsePlayerGameResultDisplay(gameResult));
    }

    private static String parsePlayerGameResultDisplay(Map<Result, Integer> gameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        parseResultToMessage(stringBuilder, gameResult.get(Result.WIN), Result.WIN);
        parseResultToMessage(stringBuilder, gameResult.get(Result.DRAW), Result.DRAW);
        parseResultToMessage(stringBuilder, gameResult.get(Result.LOSE), Result.LOSE);
        return stringBuilder.toString();
    }

    private static void parseResultToMessage(StringBuilder stringBuilder, Integer winningCount, Result result) {
        if (winningCount > 0) {
            stringBuilder.append(String.format("%d%s ", winningCount, parseResultToMessage(result)));
        }
    }

    private static void printParticipantsGameResult(Map<String, Result> participantResults) {
        participantResults
                .forEach(
                        (name, result) -> println(String.format("%s: %s", name, parseResultToMessage(result)))
                );

    }

    private static String parseResultToMessage(Result result) {
        if (result == Result.WIN) {
            return "승";
        }
        if (result == Result.LOSE) {
            return "패";
        }
        return "무";
    }

    public static void println(String message) {
        System.out.println(message);
    }

    private static void printEmptyLine() {
        println("");
    }
}
