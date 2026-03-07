package view;

import java.util.List;
import java.util.Map;
import model.Card;
import model.GameStatus;
import model.Player;

public class OutputView {
    public static void printCardOpen(List<String> names) {
        String format = "딜러와 %s에게 2장을 나누었습니다.";
        String formatedNames = String.format(format, String.join(", ", names));
        System.out.println(formatedNames);
    }

    public static void printCardByPlayers(List<Player> players) {
        players.forEach(OutputView::printCardByPlayer);
    }

    public static void printCardByPlayer(Player player) {
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        if (player.getClass().equals(Player.class)) {
            System.out.println(player.getName() + "카드: " + String.join(", ", cards));
        } else {
            Card firstCard = player.getCards().getFirst();
            String card = convert(firstCard);
            System.out.println(player.getName() + "카드: " + card);
        }
    }


    public static void printCardByPlayerWithScore(Player player) {
        int sum = player.calculateTotalScore();
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.print(player.getName() + "카드: " + String.join(", ", cards));
        System.out.println(" - 결과: " + sum);
    }

    private static String convert(Card card) {
        int value = card.getScore();
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

    public static void printFinalResultHeader() {
        System.out.println();
        System.out.println("##최종 승패");
    }

    public static void printDealerResult(int winCount, int loseCount, int drawCount) {
        System.out.print("딜러: ");
        StringBuilder result = new StringBuilder();
        if (winCount > 0)
            result.append(winCount).append("승 ");
        if (drawCount > 0)
            result.append(drawCount).append("무 ");
        if (loseCount > 0)
            result.append(loseCount).append("패");
        System.out.println(result.toString().trim());
    }

    public static void printResultByPlayers (Map<String, GameStatus> result){
        result.forEach((name, status) -> System.out.printf("%s: %s%n", name, status.getName()));
    }
}