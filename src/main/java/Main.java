public class Main {
    public static void main(String[] args) {
//        String[] deck = {"1", "2", "K"};
//        String[] deck2 = {"K", "Q", "J"};
//        String[] deck3 = {"1", "6", "K"};
//
//        System.out.println(calculate(deck));
//        System.out.println(calculate(deck2));
//        System.out.println(calculate(deck3));
    }

    public static int calculate(String[] deck) {
        int sum = 0;
        for (String card : deck) sum += modifyScore(card);

        return sum;
    }

    private static int modifyScore(String card) {
        final String CARD_DELIMITER = "[KQJ]";

        if (card.matches(CARD_DELIMITER)) {
            return 10;
        }
        if (card.matches("\\d")) {
            return Integer.parseInt(card);
        }
        return 0;
    }
}
