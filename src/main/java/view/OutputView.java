package view;

import java.util.List;
import model.Card;
import model.Participant;

public class OutputView {
    public static void printCardOpen(List<String> names) {
        String format = "딜러와 %s에게 2장을 나누었습니다.";
        String formatedNames = String.format(format, String.join(", ", names));
        System.out.println(formatedNames);
    }

    public static void printCardByPlayers(List<Participant> players) {
        players.forEach(OutputView::printCardByPlayer);
    }

    public static void printCardByPlayer(Participant player) {
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.println(player.getName() + "카드: " + String.join(", ", cards));
    }

    public static void printCardByDealer(Participant dealer) {
        Card firstCard = dealer.getCards().getFirst();
        String card = convert(firstCard);
        System.out.println("딜러카드: " + card);
    }

    public static void printCardByParticipantsWithScore(Participant player, int sum) {
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.print(player.getName() + "카드: " + String.join(", ", cards));
        System.out.println(" - 결과: "+sum);
    }

    private static String convert(Card card) {
        int value = card.value().getValue();
        String convertedValue = switch (value) {
            case 1 -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(value);
        };
        return convertedValue + card.shape().getShape();
    }

    public static void printToOpenNewCard(String name, int base) {
        System.out.println();
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.", name, base);
    }

    // TODO 리팩토링 대상
    public static void printBlank() {
        System.out.println();
    }
}
