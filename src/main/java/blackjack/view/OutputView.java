package blackjack.view;

import blackjack.model.blackjack.Record;
import blackjack.model.blackjack.Result;
import blackjack.model.blackjack.Score;
import blackjack.model.card.Card;
import blackjack.model.cards.Cards;
import blackjack.model.player.Name;
import blackjack.model.player.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {}

    public static void printOpenCardMessage(Name dealerName, List<Name> playerNames) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealerName.value(), formattedPlayerNames(playerNames));
    }

    private static String formattedPlayerNames(List<Name> names) {
        return names.stream()
            .map(Name::value)
            .collect(Collectors.joining(", "));
    }

    public static void printOpenCard(Name name, Cards cards) {
        System.out.printf("%s: %s%n", name.value(), formattedCardsText(cards));
    }

    public static void printCards(Name name, Cards cards) {
        System.out.printf("%s: %s%n", name.value(), formattedCardsText(cards));
    }

    private static String formattedCardsText(Cards openCard) {
        return openCard.stream()
            .map(OutputView::cardText)
            .collect(Collectors.joining(", "));
    }

    private static String cardText(Card card) {
        return card.rank().symbol() + card.suit().symbol();
    }

    public static void printCard(Player player) {
        System.out.printf("%s: %s%n", player.name(), takenCards(player));
    }

    private static String takenCards(Player player) {
        return player.cards().stream()
            .map(OutputView::cardText)
            .collect(Collectors.joining(", "));
    }

    public static void printDealerTakeCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printTotalScore(Name name, Cards cards, Score score) {
        System.out.printf("%s 카드: %s - 결과: %d%n", name.value(), formattedCardsText(cards), score.getValue());
    }

    public static void printDealerRecord(Record record) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d무 %d패%n", record.countBy(Result.WIN),
            record.countBy(Result.DRAW), record.countBy(Result.LOSS));
    }

    public static void printPlayerRecord(Record record) {
        System.out.printf("%s: %s%n", record.name().value(), result(record));
    }

    private static String result(Record record) {
        if (record.countBy(Result.WIN) == 1) {
            return Result.WIN.symbol();
        } else if (record.countBy(Result.DRAW) == 1) {
            return Result.DRAW.symbol();
        }
        return Result.LOSS.symbol();
    }
}
