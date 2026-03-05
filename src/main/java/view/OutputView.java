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

    public static void printCardByPlayer(List<Participant> players) {
        players.forEach(player -> {
            List<String> cards = player.getCards()
                    .stream()
                    .map(OutputView::convert)
                    .toList();
            System.out.println(player.getName() + "카드: " + String.join(", ", cards));
        });
    }

    public static void printCardByDealer(Participant dealer) {
        Card firstCard = dealer.getCards().getFirst();
        String card = convert(firstCard);
        System.out.println("딜러카드: " + card);
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
}
