import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> cards = List.of("J", "1", "Q", "K", "10", "8");

        for (String card : cards) {
            System.out.println("카드 " + card + "의 점수: " + calculateCardScore(card));
        }
    }

    public static int calculateCardScore(String card) {
        if (card.equals("J") || card.equals("Q") || card.equals("K")) {
            return 10;
        }
        return Integer.parseInt(card);
    }
}
