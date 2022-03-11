package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import model.Card;
import model.Dealer;
import model.Participator;
import model.Participators;
import model.PlayerName;
import model.Result;
import view.util.CardConvertor;
import view.util.ResultConvertor;

public class OutputView {

    private static final String SUFFIX_SHARE_CARDS_MESSAGE = "에게 %d장을 나누었습니다.";
    private static final String NAME_CARD_DELIMITER = ": ";
    private static final String DELIMITER = ", ";

    public static void printInitResult(Participators participators, int initCardCount) {
        printPrefixInitResult(participators, initCardCount);
        printHasCards(participators.findDealer());
        participators.findPlayers().forEach(OutputView::printHasCards);
    }

    public static void printHasCards(Participator participator) {
        final StringBuilder sb = new StringBuilder();
        sb.append(participator.getPlayerName().getValue())
                .append(NAME_CARD_DELIMITER)
                .append(convertToString(participator.getCards()));
        System.out.println(sb);
    }

    public static String convertToString(Participator participator) {
        final StringBuilder sb = new StringBuilder();
        sb.append(participator.getPlayerName().getValue())
                .append(NAME_CARD_DELIMITER)
                .append(convertToString(participator.getCards()));

        return sb.toString();
    }

    private static String convertToString(List<Card> cards) {
        return cards
                .stream()
                .map(CardConvertor::getCardString)
                .collect(Collectors.joining(DELIMITER));
    }

    private static void printPrefixInitResult(Participators participators, int initCardCount) {
        final StringBuilder sb = new StringBuilder();
        sb.append("딜러와 ")
                .append(String.join(", ", participators.getPlayerNames()))
                .append(SUFFIX_SHARE_CARDS_MESSAGE)
                .append("%n");
        System.out.printf(sb.toString(), initCardCount);
    }

    public static void printDealerReceiveCard() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public static void printAllCardsAndCardSum(Participators participators) {
        final StringBuilder stringBuilder = new StringBuilder();
        Dealer dealer = participators.findDealer();
        stringBuilder.append(convertToString(dealer))
                .append(" - 결과: ")
                .append(dealer.getSum())
                .append("%n");
        for (Participator player : participators.findPlayers()) {
            stringBuilder.append(convertToString(player))
                    .append(" - 결과: ")
                    .append(player.getSum())
                    .append("%n");
        }
        System.out.printf(stringBuilder.toString() + "%n");
    }

    public static void printFinalResult(
            Map<PlayerName, List<Result>> dealerFinalMatchResult,
            Map<PlayerName, Result> playerFinalMatchResult) {
        System.out.println("## 최종 승패");
        printDealerMatchResult(dealerFinalMatchResult);
        printPlayersMatchResult(playerFinalMatchResult);
    }

    private static void printPlayersMatchResult(Map<PlayerName, Result> playerFinalMatchResult) {
        for (Entry<PlayerName, Result> stringStringEntry : playerFinalMatchResult.entrySet()) {
            printResult(stringStringEntry.getKey().getValue(), ResultConvertor.convert(stringStringEntry.getValue()));
        }
    }

    private static void printResult(String key, String value) {
        System.out.println(key + ": " + value);
    }

    private static void printDealerMatchResult(Map<PlayerName, List<Result>> dealerResult) {
        for (Entry<PlayerName, List<Result>> entry : dealerResult.entrySet()) {
            printResult(entry.getKey().getValue(), ResultConvertor.convert(entry.getValue()));
        }
    }

    private static void printResult(String key, List<String> value) {
        StringBuilder sb = new StringBuilder();
        sb.append(key)
                .append(": ")
                .append(convertDealerResult(value));
        System.out.println(sb);
    }

    private static String convertDealerResult(List<String> value) {
        StringBuilder sb = new StringBuilder();
        if (value.contains("승")) {
            final int count = (int) value.stream().filter(result -> result.equals("승")).count();
            sb.append(count + "승");
        }
        if (value.contains("패")) {
            final int count = (int) value.stream().filter(result -> result.equals("패")).count();
            sb.append(count + "패");
        }
        if (value.contains("무")) {
            final int count = (int) value.stream().filter(result -> result.equals("무")).count();
            sb.append(count + "무");
        }
        return sb.toString();
    }
}
