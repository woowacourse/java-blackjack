package blackjack.view;

import blackjack.model.blackjack.Record;
import blackjack.model.blackjack.Result;
import blackjack.model.card.Card;
import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printOpenCardMessage(String dealerName, List<String> playerNames) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealerName,
            formattedPlayerNames(playerNames));
    }

    private static String formattedPlayerNames(List<String> names) {
        return names.stream()
            .collect(Collectors.joining(", "));
    }

    public static void printOpenCard(String name, Cards cards) {
        System.out.printf("%s: %s%n", name, formattedCardsText(cards));
    }

    public static void printCards(String name, Cards cards) {
        System.out.printf("%s: %s%n", name, formattedCardsText(cards));
    }

    private static String formattedCardsText(Cards openCard) {
        return openCard.values().stream()
            .map(OutputView::cardText)
            .collect(Collectors.joining(", "));
    }

    private static String cardText(Card card) {
        return card.rank().symbol() + card.suit().symbol();
    }

    public static void printDealerTakeCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printTotalScore(String name, Cards cards, Score score) {
        System.out.printf("%s 카드: %s - 결과: %d%n", name, formattedCardsText(cards),
            score.getValue());
    }

    public static void printRecords(List<Record> records) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d무 %d패%n", countByResult(records, Result.LOSS),
            countByResult(records, Result.DRAW), countByResult(records, Result.WIN));

        records.stream().
            forEach(record -> printEachPlayerRecord(record.name(), record.result()));
    }

    private static int countByResult(List<Record> records, Result result) {
        return (int) records.stream()
            .map(Record::result)
            .filter(r -> r == result).count();
    }

    private static void printEachPlayerRecord(String name, Result result) {
        System.out.printf("%s: %s%n", name, result.symbol());
    }
}
