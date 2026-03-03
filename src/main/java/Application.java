public class Application {
    public static void main(String[] args) {
        System.out.println("카드 8의 점수: " + calculateCardScore("8"));
        System.out.println("카드 K의 점수: " + calculateCardScore("K"));
    }

    private static int calculateCardScore(String card) {
        if (card.equals("J") || card.equals("Q") || card.equals("K")) {
            return 10;
        }
        return Integer.parseInt(card);
    }
}
